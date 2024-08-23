package org.babyfish.jimmer.sql.example.kt.runtime.cache

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.babyfish.jimmer.sql.event.binlog.BinLog
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


// -----------------------------
// If you are a beginner, please ignore this class,
// for non-cache mode, this class will never be used.
// -----------------------------
@ConditionalOnProperty(
    name = ["spring.profiles.active"],
    havingValue = "debezium"
)
@Component
class DebeziumListener(sqlClient: KSqlClient) {

    private val binLog: BinLog = sqlClient.binLog

    @KafkaListener(topicPattern = """debezium\..*""")
    fun onDebeziumEvent(
        @Payload(required = false) json: String?,
        acknowledgment: Acknowledgment
    ) {
        if (json !== null) { // Debezium sends an empty message after deleting a message
            val node: JsonNode = MAPPER.readTree(json)
            val tableName: String = node["source"]["table"].asText()
            binLog.accept(
                tableName,
                node["before"],
                node["after"]
            )
        }
        acknowledgment.acknowledge()
    }

    companion object {
        private val MAPPER = ObjectMapper()
    }
}

/*----------------Documentation Links----------------
https://babyfish-ct.github.io/jimmer-doc/docs/mutation/trigger#listen-to-message-queue
---------------------------------------------------*/

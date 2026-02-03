package org.babyfish.jimmer.sql.example.runtime.cache;

import org.babyfish.jimmer.jackson.codec.JsonCodec;
import org.babyfish.jimmer.jackson.codec.Node;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.event.binlog.BinLog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

// -----------------------------
// If you are a beginner, please ignore this class,
// for non-cache mode, this class will never be used.
// -----------------------------
@ConditionalOnProperty(
        name = "spring.profiles.active",
        havingValue = "debezium"
)
@Component
public class DebeziumListener {

    private static final JsonCodec<?> CODEC =
            JsonCodec.jsonCodec();

    private final BinLog binLog;

    public DebeziumListener(JSqlClient sqlClient) {
        this.binLog = sqlClient.getBinLog();
    }

    @KafkaListener(topicPattern = "debezium\\..*")
    public void onDebeziumEvent(
            @Payload(required = false) String json,
            Acknowledgment acknowledgment
    ) throws Exception {
        if (json != null) { // Debezium sends an empty message after deleting a message
            Node node = CODEC.treeReader().read(json);
            String tableName = node.get("source").get("table").castTo(String.class);
            binLog.accept(
                    tableName,
                    node.get("before"),
                    node.get("after")
            );
        }
        acknowledgment.acknowledge();
    }
}

/*----------------Documentation Links----------------
https://babyfish-ct.github.io/jimmer-doc/docs/mutation/trigger#listen-to-message-queue
---------------------------------------------------*/
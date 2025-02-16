package org.babyfish.jimmer.sql.example.kt.cfg

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.cfg.KInitializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.InputStreamReader
import javax.sql.DataSource

@Component
class DbInitializer(
    private val dataSource: DataSource,
    @param:Value("\${spring.datasource.url}") private val url: String
) : KInitializer {

    override fun initialize(dsl: KSqlClient) {
        if (url.startsWith("jdbc:h2:")) {
            initH2()
        }
    }

    @Throws(Exception::class)
    private fun initH2() {
        dataSource.connection.use { con ->
            val inputStream = DbInitializer::class.java
                .getClassLoader()
                .getResourceAsStream("h2-database.sql") ?: throw RuntimeException("no `h2-database.sql`")
            InputStreamReader(inputStream).use { reader ->
                val buf = CharArray(1024)
                val builder = StringBuilder()
                while (true) {
                    val len = reader.read(buf)
                    if (len == -1) {
                        break
                    }
                    builder.appendRange(buf, 0, len)
                }
                con.createStatement().execute(builder.toString())
            }
        }
    }
}
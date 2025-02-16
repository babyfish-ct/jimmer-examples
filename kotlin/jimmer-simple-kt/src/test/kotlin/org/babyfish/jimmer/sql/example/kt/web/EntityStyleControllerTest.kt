package org.babyfish.jimmer.sql.example.kt.web

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class EntityStyleControllerTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun testFindSimpleBooks() {
        mockMvc.get("/entity/books/simple") {
            param("name", "GraphQL in Action")
        }.andExpect {
            status { isOk() }
            content {
                json(
                    resourceContent("simple_books_with_graphql_in_action.json"),
                    strict = true
                )
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindComplexBooks() {
        mockMvc.get("/entity/books/complex") {
            param("name", "GraphQL in Action")
        }.andExpect {
            status { isOk() }
            content {
                json(
                    resourceContent("complex_books_with_graphql_in_action.json"),
                    strict = true
                )
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindSimpleRootTreeNodes() {
        mockMvc.get("/entity/rootTreeNodes/simple").andExpect {
            status { isOk() }
            content {
                json(
                    resourceContent("simple_root_tree_nodes.json"),
                    strict = true
                )
            }
        }
    }

    @Test
    @Throws(Exception::class)
    fun testFindRecursiveRootTreeNodes() {
        mockMvc.get("/entity/rootTreeNodes/recursive").andExpect {
            status { isOk() }
            content {
                json(
                    resourceContent("recursive_root_tree_nodes.json"),
                    strict = true
                )
            }
        }
    }
}
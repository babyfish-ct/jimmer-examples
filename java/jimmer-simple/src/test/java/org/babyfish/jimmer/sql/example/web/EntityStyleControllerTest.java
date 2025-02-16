package org.babyfish.jimmer.sql.example.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.babyfish.jimmer.sql.example.web.ResourceContents.contentOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class EntityStyleControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public EntityStyleControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testFindSimpleBooks() throws Exception {
        mockMvc
                .perform(
                        get("/entity/books/simple")
                                .param("name", "GraphQL in Action")
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                contentOf("simple_books_with_graphql_in_action.json"),
                                true
                        )
                );
    }

    @Test
    public void testFindComplexBooks() throws Exception {
        mockMvc
                .perform(
                        get("/entity/books/complex")
                                .param("name", "GraphQL in Action")
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                contentOf("complex_books_with_graphql_in_action.json"),
                                true
                        )
                );
    }

    @Test
    public void testFindSimpleRootTreeNodes() throws Exception {
        mockMvc
                .perform(
                        get("/entity/rootTreeNodes/simple")
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                contentOf("simple_root_tree_nodes.json"),
                                true
                        )
                );
    }

    @Test
    public void testFindRecursiveRootTreeNodes() throws Exception {
        mockMvc
                .perform(
                        get("/entity/rootTreeNodes/recursive")
                )
                .andExpect(status().isOk())
                .andExpect(
                        content().json(
                                contentOf("recursive_root_tree_nodes.json"),
                                true
                        )
                );
    }
}

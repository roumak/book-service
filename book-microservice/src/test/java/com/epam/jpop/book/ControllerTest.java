package com.epam.jpop.book;

import com.epam.jpop.bookmicroservice.controller.BookRestController;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    @Autowired
    private MockMvc theMockMvc;

    @Mock
    private BookService theBookService;

    @InjectMocks
    private BookRestController theBookRestController;

    JacksonTester<Book> bookJacksonTester;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, ObjectMapper::new);
        theMockMvc= MockMvcBuilders.standaloneSetup(theBookRestController).build();
    }

    @Test
    public void getAllBooks()throws Exception{
        List<Book> bookList = Arrays.asList(new Book("Scion of iksavaku", "Aamish Tripathi","Fiction",""));
        Mockito.when(theBookService.getAllBooks()).thenReturn(bookList);

        theMockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName", Matchers.is("Scion of iksavaku")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authorName", Matchers.is("Aamish Tripathi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("Fiction")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("")));
        Mockito.verify(theBookService,Mockito.times(1)).getAllBooks();
        Mockito.verifyNoMoreInteractions(theBookService);

    }
    @Test
    public void getBooksByIdTest()throws Exception{
        Book book = new Book(2l,"Sita, worrior of mithila", "Aamish Tripathi","Fiction","");
        Mockito.when(theBookService.getBookById(2l)).thenReturn(book);

        theMockMvc.perform(MockMvcRequestBuilders.get("/books/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("bookName", Matchers.is("Sita, worrior of mithila")))
                .andExpect(MockMvcResultMatchers.jsonPath("authorName", Matchers.is("Aamish Tripathi")))
                .andExpect(MockMvcResultMatchers.jsonPath("category", Matchers.is("Fiction")))
                .andExpect(MockMvcResultMatchers.jsonPath("description", Matchers.is("")));
        Mockito.verify(theBookService,Mockito.times(1)).getBookById(2l);
        Mockito.verifyNoMoreInteractions(theBookService);

    }
    @Test
    public void saveBookTest()throws Exception{



        Book book = new Book(45l,"SAM Manekshaw", "Manekshaw","Reality","");
        String requestBody="";
        Mockito.when(theBookService.saveBook(book)).thenReturn(book);

        theMockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"bookId\": 8,\n" +
                        "    \"bookName\": \"SAM Manekshaw\",\n" +
                        "    \"authorName\": \"Manekshaw\",\n" +
                        "    \"category\": \"Reality\",\n" +
                        "    \"description\": \"\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("bookName", Matchers.is("SAM Manekshaw")))
                .andExpect(MockMvcResultMatchers.jsonPath("authorName", Matchers.is("Manekshaw")))
                .andExpect(MockMvcResultMatchers.jsonPath("category", Matchers.is("Reality")))
                .andExpect(MockMvcResultMatchers.jsonPath("description", Matchers.is(""))
                );
        Mockito.verify(theBookService,Mockito.times(1)).saveBook(book);
        Mockito.verifyNoMoreInteractions(theBookService);

    }


}

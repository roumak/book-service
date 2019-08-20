package com.epam.jpop.book;

import com.epam.jpop.bookmicroservice.controller.BookRestController;
import com.epam.jpop.bookmicroservice.dto.BookDto;
import com.epam.jpop.bookmicroservice.model.Book;
import com.epam.jpop.bookmicroservice.services.BookService;
import com.epam.jpop.bookmicroservice.util.ObjectConverterUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
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

    private MockMvc theMockMvc;

    @Mock
    private BookService theBookService;

    @InjectMocks
    private BookRestController theBookRestController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, ObjectMapper::new);
        theMockMvc= MockMvcBuilders.standaloneSetup(theBookRestController).build();
    }

    @Test
    public void getAllBooks()throws Exception{
        Book book1=Book.builder().bookId(1L).bookName("Scion of iksavaku").authorName("Amish Tripathi")
                .category("Fiction").description("").build();
        Book book2=Book.builder().bookId(2L).bookName("The Man and his times").authorName("Sam Manekshaw")
                .category("Autobioraphy").description("Story of himself").build();

        List<Book> bookList = Arrays.asList(book1,book2);

        Mockito.when(theBookService.getAllBooks()).thenReturn(ObjectConverterUtil.convertAll(bookList, BookDto.class));

        theMockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName", Matchers.is("Scion of iksavaku")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].authorName", Matchers.is("Aamish Tripathi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].category", Matchers.is("Fiction")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description", Matchers.is("")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bookName", Matchers.is("The Man and his times")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].authorName", Matchers.is("Sam Manekshaw")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].category", Matchers.is("Autobioraphy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].description", Matchers.is("Story of himself")));
        Mockito.verify(theBookService,Mockito.times(1)).getAllBooks();
        Mockito.verifyNoMoreInteractions(theBookService);

    }
    @Test
    public void getBooksByIdTest()throws Exception{
        Book book =Book.builder().bookId(2L).bookName("Sita, worrior of mithila").authorName("Aamish Tripathi")
                .category("Fiction").description("").build();

        Mockito.when(theBookService.getBookById(2L)).thenReturn(ObjectConverterUtil.convert(book,BookDto.class));

        theMockMvc.perform(MockMvcRequestBuilders.get("/api/books/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("bookName", Matchers.is("Sita, worrior of mithila")))
                .andExpect(MockMvcResultMatchers.jsonPath("authorName", Matchers.is("Aamish Tripathi")))
                .andExpect(MockMvcResultMatchers.jsonPath("category", Matchers.is("Fiction")))
                .andExpect(MockMvcResultMatchers.jsonPath("description", Matchers.is("")));
        Mockito.verify(theBookService,Mockito.times(1)).getBookById(2L);
        Mockito.verifyNoMoreInteractions(theBookService);

    }
    @Test
    @Ignore
    public void saveBookTest()throws Exception{
        Book book = Book.builder().bookId(45L).bookName("SAM Manekshaw").authorName("Manekshaw").category("Reality")
                .description("").build();
        Mockito.when(theBookService.saveBook(ObjectConverterUtil.convert(book,BookDto.class)))
                .thenReturn(ObjectConverterUtil.convert(book,BookDto.class));

        theMockMvc.perform(MockMvcRequestBuilders.post("/api/books")
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
        Mockito.verify(theBookService,Mockito.times(1)).saveBook(ObjectConverterUtil.convert(book,BookDto.class));
        Mockito.verifyNoMoreInteractions(theBookService);

    }


}

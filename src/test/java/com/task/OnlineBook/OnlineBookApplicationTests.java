package com.task.OnlineBook;

import com.task.OnlineBook.controller.BookController;
import com.task.OnlineBook.dto.Bookdto;
import com.task.OnlineBook.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
class OnlineBookApplicationTests {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	@Autowired
	private BookService bookService;


	@Test
	public void testGetAllBooks() throws Exception {
		Bookdto book1 = new Bookdto(1, "Book 1", "Author 1", "Fiction", true);
		Bookdto book2 = new Bookdto(2, "Book 2", "Author 2", "Mystery", true);
		List<Bookdto> books = Arrays.asList(book1, book2);

		when(bookService.getAllBooks()).thenReturn(books);

		mockMvc.perform(get("/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].title").value("Book 1"))
				.andExpect(jsonPath("$[1].title").value("Book 2"));
	}

	@Test
	public void testGetBooksByCategory() throws Exception {
		Bookdto book1 = new Bookdto(1, "Book 1", "Author 1", "Fiction", true);
		Bookdto book2 = new Bookdto(2, "Book 2", "Author 2", "Fiction", true);
		List<Bookdto> fictionBooks = Arrays.asList(book1, book2);

		when(bookService.getBooksByCategory("Fiction")).thenReturn(fictionBooks);

		mockMvc.perform(get("/books/category/Fiction"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].title").value("Book 1"))
				.andExpect(jsonPath("$[1].title").value("Book 2"));

		verify(bookService, times(1)).getBooksByCategory("Fiction");
		verifyNoMoreInteractions(bookService);
	}

	@Test
	public void testBorrowBook() throws Exception {
		Bookdto borrowedBook = new Bookdto(1, "Borrowed Book", "Author", "Mystery", false);

		when(bookService.borrowBook(1)).thenReturn(borrowedBook);

		mockMvc.perform(post("/books/1/borrow"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Borrowed Book"))
				.andExpect(jsonPath("$.stock").value(false));

		verify(bookService, times(1)).borrowBook(1);
		verifyNoMoreInteractions(bookService);
	}

	@Test
	public void testReturnBook() throws Exception {
		Bookdto returnedBook = new Bookdto(1, "Returned Book", "Author", "Mystery", true);

		when(bookService.returnBook(1)).thenReturn(returnedBook);

		mockMvc.perform(post("/books/1/return"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Returned Book"))
				.andExpect(jsonPath("$.stock").value(true));

		verify(bookService, times(1)).returnBook(1);
		verifyNoMoreInteractions(bookService);
	}

}

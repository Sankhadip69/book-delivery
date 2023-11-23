package com.springframework.bookdelivery.service;

import com.springframework.bookdelivery.dto.BookDTO;
import com.springframework.bookdelivery.payload.request.book.BookCreateRequest;
import com.springframework.bookdelivery.payload.request.book.BookUpdateRequest;
import com.springframework.bookdelivery.payload.request.book.BookUpdateStockRequest;
import com.springframework.bookdelivery.payload.request.pagination.PaginationRequest;
import org.springframework.data.domain.Page;

public interface BookService {

    /**
     * Creates a new book based on the provided request.
     *
     * @param request The request containing book information.
     * @return A {@link BookDTO} representing the newly created book.
     */
    BookDTO createBook(BookCreateRequest request);

    /**
     * Retrieves a book by its unique identifier.
     *
     * @param bookId The unique identifier of the book.
     * @return A {@link BookDTO} representing the requested book.
     */
    BookDTO getBookById(String bookId);

    /**
     * Updates the stock quantity of a book by its unique identifier.
     *
     * @param bookId The unique identifier of the book.
     * @param request The request containing the updated stock information.
     * @return A {@link BookDTO} representing the book after the stock update.
     */
    BookDTO updateBookStockById(String bookId, BookUpdateStockRequest request);

    /**
     * Retrieves a paginated list of all books based on the provided request.
     *
     * @param paginationRequest The request containing pagination information.
     * @return A {@link Page} of {@link BookDTO} objects representing the list of books.
     */
    Page<BookDTO> getAllBooks(PaginationRequest paginationRequest);

    /**
     * Updates a book by its unique identifier.
     *
     * @param bookId The unique identifier of the book.
     * @param request The request containing the updated book information.
     * @return A {@link BookDTO} representing the book after the update.
     */
    BookDTO updateBookById(String bookId, BookUpdateRequest request);

    /**
     * Checks if a given amount of a book is available in stock.
     *
     * @param bookDTO The {@link BookDTO} representing the book to check.
     * @param amount The amount of the book to check for availability.
     * @return {@code true} if the specified amount is available in stock, {@code false} otherwise.
     */
    boolean isStockAvailable(BookDTO bookDTO, int amount);

}

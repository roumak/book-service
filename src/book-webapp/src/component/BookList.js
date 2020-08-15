import React, { Component } from "react";

import { Card, Table } from "react-bootstrap";

import Axios from "axios";

export default class BookList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      books: [],
    };
  }

  componentDidMount() {
    Axios.get("http://localhost:7090/api/books").then((response) => {
      this.setState({ books: response.data.books });
    });
  }

  render() {
    return (
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>Book List</Card.Header>
        <Card.Body>
          <Table bordered hover striped variant="dark">
            <thead>
              <tr>
                <th>#</th>
                <th>Book Name</th>
                <th>Author Name</th>
                <th>Category</th>
                <th>Description</th>
                <th>BookCount</th>
              </tr>
            </thead>
            <tbody>
              {this.state.books.length === 0 ? (
                <tr align="center">
                  <td colSpan="6">No books available</td>
                </tr>
              ) : (
                this.state.books.map((book) => (
                  <tr key={book.bookId}>
                    <td>{book.bookId}</td>
                    <td>{book.bookTitle}</td>
                    <td>{book.authorName}</td>
                    <td>{book.category}</td>
                    <td>{book.description}</td>
                    <td>{book.count}</td>
                  </tr>
                ))
              )}
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    );
  }
}

import React, { Component } from "react";

import { Card, Button, Table } from "react-bootstrap";

export default class BookList extends Component {
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
              <tr>
                <td>1</td>
                <td>No</td>
                <td>Books</td>
                <td>Available</td>
                <td>here</td>
                <td>Now</td>
              </tr>
              <tr align="center">
                <td colSpan="6">No books available</td>
              </tr>
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    );
  }
}

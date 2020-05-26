import React, { Component } from "react";

import { Card, Button, Form, Row, Col } from "react-bootstrap";

export default class Book extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "",
      author: "",
      category: "",
      count: "",
      description: "",
    };

    // this.bookOnChange = this.bookOnChange.bind(this);
    // this.submitBook = this.submitBook.bind(this);
  }

  submitBook(event) {
    // alert(this.state.title);
    // event.preventDefault();
  }
  bookOnChange(event) {
    // this.setState({
    //   [event.target.name]: event.target.value,
    // });
  }
  render() {
    return (
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>Book List</Card.Header>
        <Card.Body>
          <Form onSubmit={this.submitBook} id="bookForm">
            <Form.Row>
              <Form.Group as={Col} sm="6">
                <Form.Row>
                  <Form.Label as={Col} sm="3">
                    Book Title
                  </Form.Label>
                  <Col>
                    <Form.Control
                      name="title"
                      //value={this.state.title}
                      // onChange={this.bookOnChange}
                      sm="3"
                      type="text"
                      placeholder="Enter book Title"
                      className={"bg-dark text-white"}
                    />
                  </Col>
                </Form.Row>
                <Form.Row>
                  <Form.Label as={Col} sm="3">
                    Author Name
                  </Form.Label>
                  <Col>
                    <Form.Control
                      name="author"
                      sm="3"
                      type="text"
                      placeholder="Enter Author Name"
                      className={"bg-dark text-white"}
                    />
                  </Col>
                </Form.Row>
                <Form.Row>
                  <Form.Label as={Col} sm="3">
                    Category
                  </Form.Label>
                  <Col>
                    <Form.Control
                      name="category"
                      sm="3"
                      type="text"
                      placeholder="Enter book Category"
                      className={"bg-dark text-white"}
                    />
                  </Col>
                </Form.Row>

                <Form.Row>
                  <Form.Label as={Col} sm="3">
                    Count
                  </Form.Label>
                  <Col>
                    <Form.Control
                      name="count"
                      sm="3"
                      type="number"
                      placeholder="Enter book Description"
                      className={"bg-dark text-white"}
                    />
                  </Col>
                </Form.Row>
              </Form.Group>
              <Col sm="1" />

              <Form.Group as={Col} sm="5">
                <Form.Label>Description</Form.Label>
                <Form.Control
                  name="description"
                  as="textarea"
                  rows="10"
                  type="text"
                  placeholder="Enter book Description"
                  className={"bg-dark text-white"}
                />
              </Form.Group>
            </Form.Row>

            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>
        </Card.Body>
      </Card>
    );
  }
}

// Copyright @this_rc 2020-21
import React, { Component } from "react";
import { Card, Button, Form, Col } from "react-bootstrap";
import NotificationToast from "./NotificationToast";

import Axios from "axios";

export default class Book extends Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
  }

  initialState = {
    title: "",
    author: "",
    category: "",
    count: "",
    description: "",
    message: "saved save",
    show: true,
  };

  submitBook = (event) => {
    event.preventDefault();
    const book = {
      bookName: this.state.title,
      authorName: this.state.author,
      category: this.state.category,
      count: this.state.count,
      description: this.state.description,
    };

    Axios.post("http://192.168.0.107:7090/api/books", book).then((response) => {
      this.handleResponse(response);
      this.setState(() => this.initialState);
    });
  };

  bookOnChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  resetBook = (event) => {
    this.setState(() => this.initialState);
  };

  handleResponse(response) {
    if (response.data != null) {
      this.setState({ show: true, message: "book saved successfully" });
      setTimeout(() => {
        this.setState({ show: false });
      }, 3000);
    } else {
      this.setState({ show: true, message: "book couldnot be saved" });
      setTimeout(() => {
        this.setState({ show: false });
      }, 3000);
    }
  }

  render() {
    const { title, author, category, count, description } = this.state;
    return (
      <>
        <div>
          <div style={{ display: this.state.show ? "block" : "none" }}>
            <NotificationToast
              children={{
                show: this.state.show,
                message: this.state.message,
              }}
            />
          </div>
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>Book List</Card.Header>
            <Card.Body>
              <Form
                onReset={this.resetBook}
                onSubmit={this.submitBook}
                id="bookForm"
              >
                <Form.Row>
                  <Form.Group as={Col} sm="6">
                    <Form.Row>
                      <Form.Label as={Col} sm="3">
                        Book Title
                      </Form.Label>
                      <Col>
                        <Form.Control
                          name="title"
                          value={title}
                          onChange={this.bookOnChange}
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
                          value={author}
                          onChange={this.bookOnChange}
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
                          value={category}
                          onChange={this.bookOnChange}
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
                          value={count}
                          onChange={this.bookOnChange}
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
                      value={description}
                      onChange={this.bookOnChange}
                      name="description"
                      as="textarea"
                      rows="10"
                      type="text"
                      placeholder="Enter book Description"
                      className={"bg-dark text-white"}
                    />
                  </Form.Group>
                </Form.Row>

                <Button size="sm" variant="primary" type="submit">
                  Submit
                </Button>
                {"   "}
                <Button size="sm" variant="info" type="reset">
                  Reset
                </Button>
              </Form>
            </Card.Body>
          </Card>
        </div>
      </>
    );
  }
}

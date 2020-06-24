import React from "react";
// import bookLogo from "../resources/bookIcon.png";
import { Navbar, Nav, Form, FormControl, Button } from "react-bootstrap";
import { Link } from "react-router-dom";

export default class NavigationBar extends React.Component {
  render() {
    return (
      <>
        <Navbar bg="dark" variant="dark">
          <Link to={""} className="navbar-brand">
            {/* <img src={bookLogo} width="20px" height="20px" /> */}
            Book Store
          </Link>
          <Nav className="mr-auto">
            <Link to={"add"} className="nav-link">
              Add Book
            </Link>
            <Link to={"list"} className="nav-link">
              Book List
            </Link>
          </Nav>
          <Form inline>
            <FormControl type="text" placeholder="Search" className="mr-sm-2" />
            <Button variant="outline-info">Search</Button>
          </Form>
        </Navbar>
      </>
    );
  }
}

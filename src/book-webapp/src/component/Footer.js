import React from "react";
import { Navbar, Container, Col } from "react-bootstrap";

export default class Footer extends React.Component {
  render() {
    const date = new Date().getFullYear();
    return (
      <Navbar fixed="bottom" bg="dark" varient="dark">
        <Container>
          <Col lg={12} className="text-centre text-muted">
            {date}-{date + 1} All Rights Reserved by @this_rc
          </Col>
        </Container>
      </Navbar>
    );
  }
}

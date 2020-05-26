import React from "react";
import { Jumbotron } from "react-bootstrap";

export default class Welcome extends React.Component {
  render() {
    return (
      <Jumbotron className="bg-dark text-white">
        <h3> Books are the best source of wisdom</h3>
        <p>- this is a lame description about books</p>
      </Jumbotron>
    );
  }
}

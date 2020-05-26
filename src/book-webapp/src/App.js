import React from "react";
import "./App.css";
import { Row, Container, Col } from "react-bootstrap";

import NavigationBar from "./component/NavigationBar";
import Welcome from "./component/Welcome";
import BookList from "./component/BookList";
import Book from "./component/Book";
import Footer from "./component/Footer";

import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

function App() {
  const marginTop = {
    margin: "20px",
  };
  return (
    <div className="App">
      <Router>
        <NavigationBar />
        <Container>
          <Row>
            <Col lg={12} style={marginTop}>
              <Switch>
                <Route path="/" exact component={Welcome} />
                <Route path="/add" exact component={Book} />
                <Route path="/list" exact component={BookList} />
              </Switch>
            </Col>
          </Row>
        </Container>
        <Footer />
      </Router>
    </div>
  );
}

export default App;

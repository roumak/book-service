import React, { Component } from "react";
import { Toast, Col } from "react-bootstrap";

export default class NotificationToast extends Component {
  render() {
    const toastCss = {
      position: "fixed",
      top: "65px",
      right: "10px",
      zIndex: "1",
      //   boxShadow: "0 4px 8px 0 rgba(0,0,0, 0.2) , 0 6px 20px 0 rgba(0,0,0,0.19)",
    };

    const show = this.props.children.show;
    const message = this.props.children.message;
    return (
      <Col xs={2} style={show ? toastCss : null}>
        <Toast
          className={"border border-success bg-success text-white"}
          show={show}
        >
          <Toast.Header className={"bg-success text-white"}>
            <strong className="mr-auto">Success</strong>
          </Toast.Header>
          <Toast.Body>{message}</Toast.Body>
        </Toast>
      </Col>
    );
  }
}

import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSignup = (e) => {
    e.preventDefault();

    axios.post("http://localhost:8080/api/auth/register", { email, password }, {
      headers: { "Content-Type": "application/json" }
    })
    .then(response => {
      alert(response.data);
      navigate("/login"); // Redirect to login page
    })
    .catch(error => {
      console.error("Signup error:", error.response);
      alert("Error signing up!");
    });
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">📝 Sign Up</h2>
      <form onSubmit={handleSignup} className="signup-form">
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit" className="btn btn-success w-100">Register</button>
      </form>
    </div>
  );
};

export default Signup;

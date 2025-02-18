import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/auth/login", { email, password })
      .then(response => {
        alert("Login successful!");
        navigate("/properties"); // Redirect to properties page
      })
      .catch(error => {
        alert("Invalid credentials");
      });
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center">🔑 Login</h2>
      <form onSubmit={handleLogin} className="login-form">
        <div className="mb-3">
          <label className="form-label">Email</label>
          <input type="email" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <button type="submit" className="btn btn-primary w-100">Login</button>
      </form>

      {/* Add Sign Up Link */}
      <p className="text-center mt-3">
        Don't have an account? <Link to="/signup">Sign up here</Link>
      </p>
    </div>
  );
};

export default Login;

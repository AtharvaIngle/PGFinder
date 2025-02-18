import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { useTheme } from "../context/ThemeContext";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/sidebar.css";

const Sidebar = () => {
  const { darkMode, toggleTheme, sidebarOpen, toggleSidebar } = useTheme();
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    alert("Logged out successfully!");
    navigate("/login");
  };

  return (
    <div className={`sidebar ${sidebarOpen ? "open" : "closed"}`}>
      <button className="toggle-btn" onClick={toggleSidebar}>
        {sidebarOpen ? "❌" : "☰"}
      </button>

      {sidebarOpen && (
        <>
          <h2 className="sidebar-title">PG Finder</h2>
          <ul>
            <li><Link to="/">🏠 Home</Link></li>
            <li><Link to="/properties">🏡 Properties</Link></li>
            <li><Link to="/bookings">📅 My Bookings</Link></li>
            <li><Link to="/login">🔑 Login</Link></li>
          </ul>

          {/* Dark Mode Toggle */}
          <button className="btn btn-outline-light mt-3 w-100" onClick={toggleTheme}>
            {darkMode ? "☀️ Light Mode" : "🌙 Dark Mode"}
          </button>

          {/* Logout Button */}
          <button className="btn btn-danger mt-3 w-100" onClick={handleLogout}>
            🚪 Logout
          </button>
        </>
      )}
    </div>
  );
};

export default Sidebar;

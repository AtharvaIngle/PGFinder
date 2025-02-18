import React, { useEffect, useState } from "react";
import axios from "axios";

const Bookings = () => {
  const [bookings, setBookings] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    axios.get("http://localhost:8080/api/bookings/user/1", { 
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(response => setBookings(response.data))
      .catch(error => console.error("Error fetching bookings:", error));
  }, []);

  return (
    <div className="container mt-4">
      <h2>📅 My Bookings</h2>
      {bookings.length === 0 ? (
        <p>No bookings yet.</p>
      ) : (
        <ul className="list-group">
          {bookings.map((booking) => (
            <li key={booking.id} className="list-group-item">
              <strong>PG:</strong> {booking.property.name} | 
              <strong> Check-in:</strong> {booking.checkInDate} | 
              <strong> Check-out:</strong> {booking.checkOutDate}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Bookings;

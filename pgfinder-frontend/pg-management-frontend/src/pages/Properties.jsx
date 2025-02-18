import React, { useEffect, useState } from "react";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import "../styles/properties.css";

const Properties = () => {
  const [properties, setProperties] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/properties")
      .then(response => setProperties(response.data))
      .catch(error => console.error("Error fetching properties:", error));
  }, []);

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">🏡 Explore PG Listings</h2>
      <div className="row">
        {properties.map((pg) => (
          <div key={pg.id} className="col-md-4">
            <div className="card property-card">
              <div className="card-body">
                <h5 className="card-title">{pg.name}</h5>
                <p className="card-text">📍 {pg.location}</p>
                <p className="card-text">💰 Price: ₹{pg.price}</p>
                <button className="btn btn-primary">Book Now</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Properties;

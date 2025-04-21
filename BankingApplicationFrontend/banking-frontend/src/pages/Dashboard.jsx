import { useEffect, useState } from "react";
import axios from "axios";

const Dashboard = () => {
  const [user, setUser] = useState({});
  const token = localStorage.getItem("token");

  useEffect(() => {
    axios.get("http://localhost:8080/api/user", {
      headers: { Authorization: `Bearer ${token}` }
    }).then((res) => setUser(res.data))
      .catch(() => alert("Error fetching user data!"));
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold">Welcome, {user.firstName}</h1>
      <p>Account Number: {user.accountNumber}</p>
      <p>Balance: ₹{user.balance}</p>
    </div>
  );
};

export default Dashboard;

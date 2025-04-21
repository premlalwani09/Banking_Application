import { useEffect, useState } from "react";
import axios from "axios";

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    axios.get("http://localhost:8080/api/transactions", {
      headers: { Authorization: `Bearer ${token}` }
    }).then((res) => setTransactions(res.data))
      .catch(() => alert("Error fetching transactions!"));
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-xl font-bold">Transaction History</h2>
      <table className="w-full mt-4 border">
        <thead>
          <tr className="bg-gray-200">
            <th>Date</th><th>Type</th><th>Amount</th><th>Status</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((tx) => (
            <tr key={tx.id}>
              <td>{tx.createdAt}</td>
              <td>{tx.transactionType}</td>
              <td>₹{tx.amount}</td>
              <td>{tx.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Transactions;

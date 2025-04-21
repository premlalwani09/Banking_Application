import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
  const [credentials, setCredentials] = useState({ email: "", password: "" });
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", credentials);
      localStorage.setItem("token", res.data.token);
      navigate("/dashboard");
    } catch (error) {
      alert("Invalid credentials!");
    }
  };

  return (
    <div className="flex h-screen justify-center items-center">
      <form onSubmit={handleLogin} className="p-6 bg-white shadow-lg rounded-lg">
        <h2 className="text-xl font-bold">Login</h2>
        <input type="email" placeholder="Email" className="w-full p-2 border mt-2" onChange={(e) => setCredentials({...credentials, email: e.target.value})} />
        <input type="password" placeholder="Password" className="w-full p-2 border mt-2" onChange={(e) => setCredentials({...credentials, password: e.target.value})} />
        <button type="submit" className="w-full bg-blue-500 text-white p-2 mt-4">Login</button>
      </form>
    </div>
  );
};

export default Login;

import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import AddStudent from "./components/AddStudent";
import Students from "./components/Students";
import Navbar from "./components/Navbar";
import UpdateStudent from "./components/UpdateStudent";

function App() {
  return (
    <>
    <BrowserRouter>
      <Navbar/>
      <Routes>
        <Route index element={<Students />} />
        <Route path="/" element={<Students />}></Route>
        <Route path="/students" element={<Students />} />
        <Route path="/addStudent" element={<AddStudent />} />
        <Route path="/editStudent/:id" element={<UpdateStudent />} />
      </Routes>
    </BrowserRouter>
  </>
  );
}

export default App;

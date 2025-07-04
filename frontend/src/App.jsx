import { BrowserRouter, Routes, Route } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import Navbar from './components/Navbar'
import Footer from './components/Footer'
import './App.css'
import RegisterPage from './components/RegisterPage'
import LoginPage from './components/LoginPage'
import { Toaster } from 'react-hot-toast'

function App() {

  return (
    <>
          <>
      <BrowserRouter>
      <Navbar />
      <Toaster />
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/about" element={<AboutPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>    </>
  )
}

export default App

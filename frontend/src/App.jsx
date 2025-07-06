// import { BrowserRouter, Routes, Route } from 'react-router-dom'
// import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
// import { Toaster } from 'react-hot-toast'

// import LandingPage from './components/LandingPage'
// import AboutPage from './components/AboutPage'
// import Navbar from './components/Navbar'
// import Footer from './components/Footer'
// import RegisterPage from './components/RegisterPage'
// import LoginPage from './components/LoginPage'
// import DashboardLayout from './components/Dashboard/DashboardLayout'

// import './App.css'

// // 🔹 Create a query client instance
// const queryClient = new QueryClient()

// function App() {
//   return (
//     <QueryClientProvider client={queryClient}>
//       <BrowserRouter>
//         <Navbar />
//         <Toaster />
//         <Routes>
//           <Route path="/" element={<LandingPage />} />
//           <Route path="/about" element={<AboutPage />} />
//           <Route path="/register" element={<RegisterPage />} />
//           <Route path="/login" element={<LoginPage />} />
//           <Route path="/dashboard" element={<DashboardLayout />} />
//         </Routes>
//         <Footer />
//       </BrowserRouter>
//     </QueryClientProvider>
//   )
// }

// export default App



import './App.css'
import { BrowserRouter as Router } from 'react-router-dom'
import { getApps } from './utils/helper'

function App() {

  const CurrentApp = getApps();

  return (
    <Router>
      <CurrentApp />
    </Router>
  )
}

export default App
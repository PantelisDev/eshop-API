import React, { useState } from 'react';
import { BrowserRouter, Routes, Route, Link, useLocation } from 'react-router-dom';
import Landing from './Pages/Landing';
import Products from './Pages/Products';
import Orders from './Pages/Orders';
import Register from './Pages/Register';
import CreateOrder from './Pages/CreateOrder';
import SignIn from './Pages/SignIn';

function Navbar({ loggedInUser, setLoggedInUser }) {
  const location = useLocation();

  return (
    <nav style={{
      display: 'flex', alignItems: 'center', justifyContent: 'space-between',
      padding: '12px 24px',
      borderBottom: '1px solid #444444',
      background: '#1a1a1a'
    }}>
      <div style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
        {[
          { path: '/', label: 'Home' },
          { path: '/products', label: 'Products' },
          { path: '/orders', label: 'Orders' },
          { path: '/register', label: 'Register' }
        ].map(link => (
          <Link key={link.path} to={link.path} style={{
            fontSize: '13px',
            color: location.pathname === link.path ? '#ffffff' : '#999999',
            fontWeight: location.pathname === link.path ? '500' : '400',
            textDecoration: 'none',
            padding: '5px 10px',
            borderRadius: '8px',
            border: location.pathname === link.path ? '1px solid #ffffff' : '1px solid transparent',
            transition: 'all 0.6s ease'
          }}>{link.label}</Link>
        ))}

        {loggedInUser ? (
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <span style={{ fontSize: '13px', color: '#ffffff' }}>
              👋 {loggedInUser.firstName}
            </span>
            <button onClick={() => setLoggedInUser(null)} style={{
              fontSize: '13px', padding: '5px 10px',
              borderRadius: '8px', border: '1px solid #e53935',
              background: 'transparent', color: '#e53935',
              cursor: 'pointer'
            }}>Sign out</button>
          </div>
        ) : (
          <Link to="/signin" style={{
            fontSize: '13px',
            color: location.pathname === '/signin' ? '#ffffff' : '#999999',
            fontWeight: location.pathname === '/signin' ? '500' : '400',
            textDecoration: 'none',
            padding: '5px 10px',
            borderRadius: '8px',
            border: location.pathname === '/signin' ? '1px solid #ffffff' : '1px solid transparent',
            transition: 'all 0.6s ease'
          }}>Sign in</Link>
        )}
      </div>
    </nav>
  );
}

function App() {
  const [loggedInUser, setLoggedInUser] = useState(null);

  return (
    <BrowserRouter>
      <div style={{ background: '#1a1a1a', minHeight: '100vh' }}>
        <Navbar loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />
        <div style={{ padding: '24px' }}>
          <Routes>
            <Route path="/" element={<Landing />} />
            <Route path="/products" element={<Products loggedInUser={loggedInUser} />} />
            <Route path="/orders" element={<Orders />} />
            <Route path="/register" element={<Register />} />
            <Route path="/create-order" element={<CreateOrder />} />
            <Route path="/signin" element={<SignIn setLoggedInUser={setLoggedInUser} />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
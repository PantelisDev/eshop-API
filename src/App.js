import React from 'react';
import { BrowserRouter, Routes, Route, Link, useLocation } from 'react-router-dom';
import Landing from './Pages/Landing';
import Products from './Pages/Products';
import Orders from './Pages/Orders';
import Register from './Pages/Register';
import CreateOrder from './Pages/CreateOrder';

function Navbar() {
  const location = useLocation();
  return (
    <nav style={{
      display: 'flex', alignItems: 'center', justifyContent: 'space-between',
      padding: '12px 24px',
      borderBottom: '1px solid #444444',
      background: '#1a1a1a'
    }}>
      <div style={{ fontSize: '16px', fontWeight: '500', color: '#ffffff' }}>🛍 Eshop</div>
      <div style={{ display: 'flex', gap: '8px' }}>
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
      </div>
    </nav>
  );
}

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <div style={{ padding: '24px' }}>
        <Routes>
          <Route path="/" element={<Landing />} />
          <Route path="/products" element={<Products />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="/register" element={<Register />} />
          <Route path="/create-order" element={<CreateOrder />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
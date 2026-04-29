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
  const [showSignOut, setShowSignOut] = useState(false);

  return (
    <nav style={{
      display: 'flex', alignItems: 'center', justifyContent: 'space-between',
      padding: '12px 24px',
      borderBottom: '1px solid #444444',
      background: '#1a1a1a'
    }}>

      {/* Sign out confirmation popup */}
      {showSignOut && (
        <div style={{
          position: 'fixed', top: 0, left: 0, right: 0, bottom: 0,
          background: 'rgba(0,0,0,0.7)',
          display: 'flex', alignItems: 'center', justifyContent: 'center',
          zIndex: 9999
        }}>
          <div style={{
            background: '#2a2a2a', border: '1px solid #444444',
            borderRadius: '12px', padding: '32px',
            textAlign: 'center', maxWidth: '320px', width: '100%'
          }}>
            <div style={{ fontSize: '18px', fontWeight: '500', color: '#ffffff', marginBottom: '8px' }}>Sign out?</div>
            <div style={{ fontSize: '13px', color: '#999999', marginBottom: '24px' }}>Are you sure you want to sign out?</div>
            <div style={{ display: 'flex', gap: '12px', justifyContent: 'center' }}>
              <button onClick={() => setShowSignOut(false)} style={{
                padding: '10px 24px', borderRadius: '8px',
                border: '1px solid #555555', background: 'transparent',
                color: '#ffffff', fontSize: '13px', cursor: 'pointer'
              }}>Cancel</button>
              <button onClick={() => { setLoggedInUser(null); setShowSignOut(false); window.location.href = '/signin'; }} style={{
                padding: '10px 24px', borderRadius: '8px',
                border: '1px solid #e53935', background: 'transparent',
                color: '#e53935', fontSize: '13px', cursor: 'pointer'
              }}>Sign out</button>
            </div>
          </div>
        </div>
      )}

      {/* Left side - nav links */}
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
      </div>

      {/* Right side */}
      <div>
        {loggedInUser ? (
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <span style={{ fontSize: '13px', color: '#ffffff' }}>
              👋 {loggedInUser.firstName}
            </span>
            <button onClick={() => setShowSignOut(true)} style={{
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
        <div>
          <Routes>
            <Route path="/"element={<Landing />} />
            <Route path="/products" element={<Products loggedInUser={loggedInUser} />} />
            <Route path="/orders" element={<Orders loggedInUser={loggedInUser} />} />    
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
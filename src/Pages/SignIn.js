import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SignIn({ setLoggedInUser }) {
  const [form, setForm] = useState({ userName: '', passWord: '' });
  const [errorMsg, setErrorMsg] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async () => {
    if (!form.userName || !form.passWord) {
      setErrorMsg('Please fill in all fields!');
      setTimeout(() => setErrorMsg(''), 3200);
      return;
    }

    const response = await fetch('https://eshop-api-production-2a1c.up.railway.app/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    });

    const data = await response.json();

    if (response.status === 401 || !data.data) {
      setErrorMsg('Invalid username or password!');
      setTimeout(() => setErrorMsg(''), 3200);
      return;
    }

    setLoggedInUser(data.data);
    navigate('/products');
  };

  return (
    <div style={{
      position: 'fixed',
      top: '60px', left: '0', right: '0', bottom: '0',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      background: '#1a1a1a',
      overflow: 'hidden'
    }}>
      {/* Orbs */}
      <div style={{ position: 'absolute', width: '400px', height: '400px', borderRadius: '50%', background: '#f97316', filter: 'blur(100px)', opacity: 0.12, top: '-100px', left: '-100px', animation: 'float1 8s ease-in-out infinite', pointerEvents: 'none' }} />
      <div style={{ position: 'absolute', width: '350px', height: '350px', borderRadius: '50%', background: '#6366f1', filter: 'blur(100px)', opacity: 0.12, bottom: '-100px', right: '-100px', animation: 'float2 8s ease-in-out infinite', pointerEvents: 'none' }} />

      <style>{`
        @keyframes float1 { 0%, 100% { transform: translate(0,0); } 50% { transform: translate(20px,-20px); } }
        @keyframes float2 { 0%, 100% { transform: translate(0,0); } 50% { transform: translate(-20px,20px); } }
        @keyframes popIn { from { opacity:0; transform:translateX(-50%) translateY(-48%); } to { opacity:1; transform:translateX(-50%) translateY(-50%); } }
        @keyframes progress { from { transform:scaleX(1); } to { transform:scaleX(0); } }
      `}</style>

      {/* Error Popup */}
      {errorMsg && (
        <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translateX(-50%) translateY(-50%)', background: '#2a2a2a', border: '1px solid #3a3a3a', borderRadius: '12px', padding: '24px 32px', display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '12px', zIndex: 9999, animation: 'popIn 0.4s ease', overflow: 'hidden', minWidth: '280px', textAlign: 'center' }}>
          <div style={{ width: '48px', height: '48px', borderRadius: '50%', background: '#3a1a1a', border: '1px solid #e53935', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <svg width="24" height="24" viewBox="0 0 16 16" fill="none"><path d="M4 4l8 8M12 4l-8 8" stroke="#e53935" strokeWidth="2" strokeLinecap="round"/></svg>
          </div>
          <div style={{ fontSize: '16px', fontWeight: '500', color: '#ffffff' }}>{errorMsg}</div>
          <div style={{ position: 'absolute', bottom: 0, left: 0, height: '3px', background: '#e53935', borderRadius: '0 0 12px 12px', width: '100%', transformOrigin: 'left', animation: 'progress 3s linear forwards' }} />
        </div>
      )}

      <div style={{ position: 'relative', zIndex: 1, background: 'rgba(42,42,42,0.85)', border: '1px solid #444444', borderRadius: '16px', padding: '32px', width: '100%', maxWidth: '400px', backdropFilter: 'blur(10px)' }}>
        <div style={{ marginBottom: '28px' }}>
          <div style={{ fontSize: '22px', fontWeight: '500', color: '#ffffff', marginBottom: '6px' }}>Sign in</div>
          <div style={{ fontSize: '14px', color: '#999999' }}>Welcome back!</div>
        </div>

        <label style={labelStyle}>Username</label>
        <input
          type="text"
          placeholder="Enter username..."
          onChange={(e) => setForm({ ...form, userName: e.target.value })}
          style={inputStyle}
        />

        <label style={labelStyle}>Password</label>
        <input
          type="password"
          placeholder="••••••••"
          onChange={(e) => setForm({ ...form, passWord: e.target.value })}
          style={inputStyle}
        />

        <button onClick={handleSubmit} style={{
          width: '100%', padding: '11px',
          borderRadius: '8px',
          border: '1px solid #f97316',
          background: 'transparent',
          color: '#f97316',
          fontSize: '14px', fontWeight: '500',
          cursor: 'pointer', marginTop: '8px'
        }}>Sign in</button>

        <hr style={{ border: 'none', borderTop: '1px solid #444444', margin: '24px 0' }} />

        <div style={{ textAlign: 'center', fontSize: '13px', color: '#999999' }}>
          Don't have an account? <span onClick={() => navigate('/register')} style={{ color: '#ffffff', fontWeight: '500', cursor: 'pointer' }}>Register</span>
        </div>
      </div>
    </div>
  );
}

const labelStyle = { fontSize: '13px', color: '#999999', display: 'block', marginBottom: '6px' };
const inputStyle = { width: '100%', padding: '9px 12px', border: '1px solid #444444', borderRadius: '8px', background: '#333333', color: '#ffffff', fontSize: '14px', outline: 'none', marginBottom: '16px' };

export default SignIn;
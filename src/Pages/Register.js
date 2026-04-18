import React, { useState, useEffect } from 'react';

function Register() {
  const [cities, setCities] = useState([]);
  const [form, setForm] = useState({
    firstName: '',
    lastName: '',
    userName: '',
    passWord: '',
    address: '',
    phoneNo: '',
    cityId: 1
  });

  useEffect(() => {
    fetch('http://localhost:8080/cities')
      .then(res => res.json())
      .then(data => setCities(data.data));
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    fetch('http://localhost:8080/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
    .then(res => res.json())
    .then(data => alert('User registered with ID: ' + data.data));
  };

  return (
    <div style={{
      position: 'fixed',
      top: '60px',
      left: '0',
      right: '0',
      bottom: '0',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      background: '#1a1a1a',
      overflow: 'hidden'
    }}>
      {/* Orbs */}
      <div style={{
        position: 'absolute', width: '400px', height: '400px',
        borderRadius: '50%', background: '#f97316',
        filter: 'blur(100px)', opacity: 0.12,
        top: '-100px', left: '-100px',
        animation: 'float1 8s ease-in-out infinite'
      }} />
      <div style={{
        position: 'absolute', width: '350px', height: '350px',
        borderRadius: '50%', background: '#6366f1',
        filter: 'blur(100px)', opacity: 0.12,
        bottom: '-100px', right: '-100px',
        animation: 'float2 8s ease-in-out infinite'
      }} />
      <div style={{
        position: 'absolute', width: '250px', height: '250px',
        borderRadius: '50%', background: '#f97316',
        filter: 'blur(80px)', opacity: 0.08,
        bottom: '50px', left: '100px',
        animation: 'float3 8s ease-in-out infinite'
      }} />

      <style>{`
        @keyframes float1 {
          0%, 100% { transform: translate(0, 0); }
          50% { transform: translate(20px, -20px); }
        }
        @keyframes float2 {
          0%, 100% { transform: translate(0, 0); }
          50% { transform: translate(-20px, 20px); }
        }
        @keyframes float3 {
          0%, 100% { transform: translate(0, 0); }
          50% { transform: translate(15px, -15px); }
        }
      `}</style>

      <div style={{
        position: 'relative', zIndex: 1,
        background: 'rgba(42, 42, 42, 0.85)',
        border: '1px solid #444444',
        borderRadius: '16px',
        padding: '32px',
        width: '100%',
        maxWidth: '480px',
        backdropFilter: 'blur(10px)',
        overflowY: 'auto',
        maxHeight: '90vh'
      }}>
        <div style={{ marginBottom: '28px' }}>
          <div style={{ fontSize: '22px', fontWeight: '500', marginBottom: '6px', color: '#ffffff' }}>Create an account</div>
          <div style={{ fontSize: '14px', color: '#999999' }}>Fill in your details to get started</div>
        </div>

        <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '16px' }}>
          <div>
            <label style={labelStyle}>First name</label>
            <input name="firstName" placeholder="John" onChange={handleChange} style={inputStyle} />
          </div>
          <div>
            <label style={labelStyle}>Last name</label>
            <input name="lastName" placeholder="Doe" onChange={handleChange} style={inputStyle} />
          </div>
          <div>
            <label style={labelStyle}>Username</label>
            <input name="userName" placeholder="johndoe" onChange={handleChange} style={inputStyle} />
          </div>
          <div>
            <label style={labelStyle}>Password</label>
            <input name="passWord" type="password" placeholder="••••••••" onChange={handleChange} style={inputStyle} />
          </div>
          <div style={{ gridColumn: '1 / -1' }}>
            <label style={labelStyle}>Address</label>
            <input name="address" placeholder="123 Main St" onChange={handleChange} style={inputStyle} />
          </div>
          <div>
            <label style={labelStyle}>Phone</label>
            <input name="phoneNo" placeholder="+30 697 123 4567" onChange={handleChange} style={inputStyle} />
          </div>
          <div>
            <label style={labelStyle}>City</label>
            <select name="cityId" onChange={handleChange} style={inputStyle}>
              {cities.map(city => (
                <option key={city.id} value={city.id}>{city.name}</option>
              ))}
            </select>
          </div>
        </div>

        <button onClick={handleSubmit} style={{
          display: 'block',
          margin: '24px auto 0',
          padding: '10px 32px',
          borderRadius: '8px',
          border: '1px solid #f97316',
          background: 'transparent',
          color: '#f97316',
          fontSize: '14px',
          fontWeight: '500',
          cursor: 'pointer'
        }}>Create account</button>

        <hr style={{ border: 'none', borderTop: '1px solid #444444', margin: '24px 0' }} />

        <div style={{ textAlign: 'center', fontSize: '13px', color: '#999999' }}>
          Already have an account? <span style={{ color: '#ffffff', fontWeight: '500', cursor: 'pointer' }}>Sign in</span>
        </div>
      </div>
    </div>
  );
}

const labelStyle = {
  fontSize: '13px',
  color: '#999999',
  display: 'block',
  marginBottom: '6px'
};

const inputStyle = {
  width: '100%',
  padding: '9px 12px',
  border: '1px solid #444444',
  borderRadius: '8px',
  background: '#333333',
  color: '#ffffff',
  fontSize: '14px',
  outline: 'none'
};

export default Register;
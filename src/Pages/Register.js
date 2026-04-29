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
  const [passwordErrors, setPasswordErrors] = useState([]);

  useEffect(() => {
    fetch('https://eshop-api-production-2a1c.up.railway.app/cities')
      .then(res => res.json())
      .then(data => setCities(data.data));
  }, []);

  const validatePassword = (password) => {
    const errors = [];
    if (password.length < 8) errors.push('At least 8 characters');
    if (!/[A-Z]/.test(password)) errors.push('At least one uppercase letter');
    if (!/[0-9]/.test(password)) errors.push('At least one number');
    return errors;
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    if (e.target.name === 'passWord') {
      setPasswordErrors(validatePassword(e.target.value));
    }
  };

  const handleSubmit = () => {
    const errors = validatePassword(form.passWord);
    if (errors.length > 0) {
      alert('Password does not meet requirements!');
      return;
    }

    fetch('https://eshop-api-production-2a1c.up.railway.app/users', {
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
      `}</style>

      <div style={{
        position: 'relative', zIndex: 1,
        background: 'rgba(42,42,42,0.85)',
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
          <div style={{ fontSize: '22px', fontWeight: '500', color: '#ffffff', marginBottom: '6px' }}>Create an account</div>
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
            <input name="passWord" type="password" placeholder="••••••••" onChange={handleChange} style={{
              ...inputStyle,
              border: form.passWord && passwordErrors.length > 0 ? '1px solid #e53935' : form.passWord && passwordErrors.length === 0 ? '1px solid #22c55e' : '1px solid #444444'
            }} />
            {form.passWord && passwordErrors.length > 0 && (
              <div style={{ marginTop: '4px' }}>
                {passwordErrors.map((err, i) => (
                  <div key={i} style={{ fontSize: '11px', color: '#e53935' }}>✕ {err}</div>
                ))}
              </div>
            )}
            {form.passWord && passwordErrors.length === 0 && (
              <div style={{ fontSize: '11px', color: '#22c55e', marginTop: '4px' }}>✓ Password is strong</div>
            )}
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
          fontSize: '14px', fontWeight: '500',
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
  fontSize: '13px', color: '#999999', display: 'block', marginBottom: '6px'
};

const inputStyle = {
  width: '100%', padding: '9px 12px',
  border: '1px solid #444444',
  borderRadius: '8px',
  background: '#333333',
  color: '#ffffff',
  fontSize: '14px', outline: 'none'
};

export default Register;
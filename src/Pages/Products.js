import React, { useState, useEffect } from 'react';

function Products() {
  const [products, setProducts] = useState([]);
  const [users, setUsers] = useState([]);
  const [hoveredId, setHoveredId] = useState(null);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [form, setForm] = useState({ userId: null, orderDate: '', password: '' });
  const [showPopup, setShowPopup] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');
  const [userName, setUserName] = useState('');

  useEffect(() => {
    fetch('https://eshop-api-production-2a1c.up.railway.app/products')
      .then(res => res.json())
      .then(data => setProducts(data.data));

    fetch('https://eshop-api-production-2a1c.up.railway.app/users')
      .then(res => res.json())
      .then(data => setUsers(data.data));
  }, []);

  const showError = (msg) => {
    setErrorMsg(msg);
    setTimeout(() => setErrorMsg(''), 3200);
  };

  const handleProductCheck = (product) => {
    if (selectedProducts.find(p => p.id === product.id)) {
      setSelectedProducts(selectedProducts.filter(p => p.id !== product.id));
    } else {
      setSelectedProducts([...selectedProducts, product]);
    }
  };

  const isSelected = (id) => selectedProducts.some(p => p.id === id);
  const total = selectedProducts.reduce((sum, p) => sum + p.price, 0).toFixed(2);

  const handleSubmit = async () => {
    const user = users.find(u => u.userName.toLowerCase() === userName.trim().toLowerCase());
    if (!user) { showError('User not found!'); return; }
    if (user.passWord !== form.password) { showError('Invalid password!'); return; }
    if (!form.orderDate) { showError('Please select an order date!'); return; }
    if (selectedProducts.length === 0) { showError('Please select at least one product!'); return; }

    const orderResponse = await fetch('https://eshop-api-production-2a1c.up.railway.app/orders', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ userId: user.id, orderDate: form.orderDate + ':00' })
    });
    const orderData = await orderResponse.json();
    const orderId = orderData.data;

    for (const product of selectedProducts) {
      await fetch('https://eshop-api-production-2a1c.up.railway.app/order-items', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ orderId, productId: product.id })
      });
    }

    setSelectedProducts([]);
    setUserName('');
    setForm({ userId: null, orderDate: '', password: '' });
    setShowPopup(true);
    setTimeout(() => setShowPopup(false), 3200);
  };

  return (
    <div style={{ position: 'relative', minHeight: '100vh', background: '#1a1a1a', overflow: 'hidden' }}>

      <style>{`
        @keyframes float1 { 0%, 100% { transform: translate(0,0); } 50% { transform: translate(20px,-20px); } }
        @keyframes float2 { 0%, 100% { transform: translate(0,0); } 50% { transform: translate(-20px,20px); } }
        @keyframes float3 { 0%, 100% { transform: translate(0,0); } 50% { transform: translate(15px,-15px); } }
        @keyframes popIn { from { opacity:0; transform:translateX(-50%) translateY(-48%); } to { opacity:1; transform:translateX(-50%) translateY(-50%); } }
        @keyframes progress { from { transform:scaleX(1); } to { transform:scaleX(0); } }
        .product-card { transition: border-color 0.3s ease, transform 0.2s ease; }
        .add-btn { transition: all 0.2s ease; }
        .add-btn:hover { background: #22c55e !important; color: #ffffff !important; }
      `}</style>

      {/* Orbs */}
      <div style={{ position: 'fixed', width: '400px', height: '400px', borderRadius: '50%', background: '#f97316', filter: 'blur(100px)', opacity: 0.12, top: '-100px', left: '-100px', animation: 'float1 8s ease-in-out infinite', pointerEvents: 'none' }} />
      <div style={{ position: 'fixed', width: '350px', height: '350px', borderRadius: '50%', background: '#6366f1', filter: 'blur(100px)', opacity: 0.12, bottom: '-100px', right: '-100px', animation: 'float2 8s ease-in-out infinite', pointerEvents: 'none' }} />
      <div style={{ position: 'fixed', width: '250px', height: '250px', borderRadius: '50%', background: '#f97316', filter: 'blur(80px)', opacity: 0.08, bottom: '50px', left: '100px', animation: 'float3 8s ease-in-out infinite', pointerEvents: 'none' }} />

      {/* Success Popup */}
      {showPopup && (
        <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translateX(-50%) translateY(-50%)', background: '#2a2a2a', border: '1px solid #3a3a3a', borderRadius: '12px', padding: '24px 32px', display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '12px', zIndex: 9999, animation: 'popIn 0.4s ease', overflow: 'hidden', minWidth: '280px', textAlign: 'center' }}>
          <div style={{ width: '48px', height: '48px', borderRadius: '50%', background: '#1a3a1a', border: '1px solid #4caf50', display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <svg width="24" height="24" viewBox="0 0 16 16" fill="none"><path d="M3 8l3.5 3.5L13 5" stroke="#4caf50" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/></svg>
          </div>
          <div style={{ fontSize: '16px', fontWeight: '500', color: '#ffffff' }}>Order placed successfully!</div>
          <div style={{ fontSize: '13px', color: '#999999' }}>Your order is being processed</div>
          <div style={{ position: 'absolute', bottom: 0, left: 0, height: '3px', background: '#4caf50', borderRadius: '0 0 12px 12px', width: '100%', transformOrigin: 'left', animation: 'progress 3s linear forwards' }} />
        </div>
      )}

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

      <div style={{ position: 'relative', zIndex: 1, padding: '24px' }}>
        <div style={{ fontSize: '22px', fontWeight: '500', marginBottom: '20px', color: '#ffffff' }}>Products</div>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(200px, 1fr))', gap: '12px' }}>
          {products.map(product => (
            <div
              key={product.id}
              className="product-card"
              style={{
                background: '#2a2a2a',
                border: isSelected(product.id) ? '1px solid #22c55e' : hoveredId === product.id ? '1px solid #f97316' : '1px solid #3a3a3a',
                borderRadius: '8px',
                overflow: 'hidden',
                cursor: 'pointer'
              }}
              onMouseEnter={() => setHoveredId(product.id)}
              onMouseLeave={() => setHoveredId(null)}
            >
              <div style={{ width: '100%', aspectRatio: '1', background: '#222222', display: 'flex', alignItems: 'center', justifyContent: 'center', overflow: 'hidden' }}>
               <img
                   src={product.imageUrl}
                   alt={product.title}
                  style={{ width: '100%', height: '210px', objectFit: 'cover', display: 'block' }}
                   onError={(e) => e.target.src = 'https://cdn-icons-png.flaticon.com/512/2529/2529396.png'}
                />
              </div>
              <div style={{ padding: '12px' }}>
                <div style={{ fontSize: '13px', fontWeight: '500', color: '#ffffff', marginBottom: '4px', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>{product.title}</div>
                <div style={{ fontSize: '11px', color: '#777777', marginBottom: '10px' }}>{product.categoryName}</div>
                <div style={{ fontSize: '18px', fontWeight: '600', color: '#f97316', marginBottom: '10px' }}>€{product.price.toFixed(2)}</div>
                <button
                  className="add-btn"
                  onClick={() => handleProductCheck(product)}
                  style={{
                    width: '100%', padding: '8px',
                    borderRadius: '6px',
                    border: '1px solid #22c55e',
                    background: isSelected(product.id) ? '#22c55e' : 'transparent',
                    color: isSelected(product.id) ? '#ffffff' : '#22c55e',
                    fontSize: '12px', fontWeight: '500',
                    cursor: 'pointer'
                  }}
                >
                  {isSelected(product.id) ? '✓ Added' : 'Add to order'}
                </button>
              </div>
            </div>
          ))}
        </div>

        <div className="order-section" style={{ marginTop: '40px', display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '24px' }}>
          <div style={{ background: 'rgba(42,42,42,0.85)', border: '1px solid #444444', borderRadius: '12px', padding: '24px', backdropFilter: 'blur(10px)' }}>
            <div style={{ fontSize: '16px', fontWeight: '500', color: '#ffffff', marginBottom: '20px' }}>Place an order</div>

            <label style={labelStyle}>Customer username *</label>
            <input
              value={userName}
              type="text"
              placeholder="Enter username..."
              onChange={(e) => {
                setUserName(e.target.value);
                const user = users.find(u => u.userName.toLowerCase() === e.target.value.trim().toLowerCase());
                if (user) setForm({ ...form, userId: user.id });
                else setForm({ ...form, userId: null });
              }}
              style={{ ...inputStyle, border: userName && !form.userId ? '1px solid #e53935' : '1px solid #444444' }}
            />
            {userName && !form.userId && <div style={{ fontSize: '12px', color: '#e53935', marginTop: '-12px', marginBottom: '12px' }}>User not found</div>}

            <label style={labelStyle}>Password *</label>
            <input value={form.password} type="password" placeholder="Enter password..." onChange={(e) => setForm({ ...form, password: e.target.value })} style={inputStyle} />

            <label style={labelStyle}>Order date *</label>
            <input type="datetime-local" onChange={(e) => setForm({ ...form, orderDate: e.target.value })} style={inputStyle} />

            <div style={{ fontSize: '14px', fontWeight: '500', color: '#ffffff', margin: '16px 0 12px' }}>Selected products</div>
            <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
              {selectedProducts.length === 0 ? (
                <div style={{ fontSize: '13px', color: '#999999' }}>No products selected yet</div>
              ) : (
                selectedProducts.map(product => (
                  <div key={product.id} style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', padding: '10px 12px', background: '#333333', border: '1px solid #22c55e', borderRadius: '8px' }}>
                    <div style={{ fontSize: '13px', color: '#ffffff' }}>{product.title}</div>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '12px' }}>
                      <div style={{ fontSize: '13px', color: '#f97316' }}>€{product.price.toFixed(2)}</div>
                      <div onClick={() => handleProductCheck(product)} style={{ fontSize: '11px', color: '#e53935', cursor: 'pointer' }}>✕</div>
                    </div>
                  </div>
                ))
              )}
            </div>
          </div>

          <div style={{ background: 'rgba(42,42,42,0.85)', border: '1px solid #444444', borderRadius: '12px', padding: '24px', alignSelf: 'start', backdropFilter: 'blur(10px)' }}>
            <div style={{ fontSize: '16px', fontWeight: '500', color: '#ffffff', marginBottom: '20px' }}>Order summary</div>
            <div style={{ background: '#333333', border: '1px solid #444444', borderRadius: '8px', padding: selectedProducts.length === 0 ? '12px 16px' : '16px' }}>
              {selectedProducts.length === 0 ? (
                <div style={{ fontSize: '13px', color: '#999999' }}>No products selected</div>
              ) : (
                selectedProducts.map(p => (
                  <div key={p.id} style={{ display: 'flex', justifyContent: 'space-between', fontSize: '13px', color: '#999999', marginBottom: '8px' }}>
                    <span>{p.title}</span>
                    <span>€{p.price.toFixed(2)}</span>
                  </div>
                ))
              )}
              {selectedProducts.length > 0 && (
                <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: '15px', fontWeight: '500', color: '#ffffff', paddingTop: '12px', borderTop: '1px solid #444444', marginTop: '8px' }}>
                  <span>Total</span>
                  <span>€{total}</span>
                </div>
              )}
            </div>
            {selectedProducts.length > 0 && (
              <button onClick={handleSubmit} style={{ width: '100%', padding: '12px', borderRadius: '8px', border: '1px solid #f97316', background: 'transparent', color: '#f97316', fontSize: '14px', fontWeight: '500', cursor: 'pointer', marginTop: '16px' }}>Place order</button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

const labelStyle = { fontSize: '13px', color: '#999999', display: 'block', marginBottom: '6px' };
const inputStyle = { width: '100%', padding: '10px 12px', border: '1px solid #444444', borderRadius: '8px', background: '#333333', color: '#ffffff', fontSize: '14px', outline: 'none', marginBottom: '16px' };

export default Products;
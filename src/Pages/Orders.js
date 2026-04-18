import React, { useState, useEffect } from 'react';

function Orders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch('https://eshop-api-production-2a1c.up.railway.app/orders')
      .then(res => res.json())
      .then(data => setOrders(data.data));
  }, []);

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

      <style>{`
        @keyframes float1 {
          0%, 100% { transform: translate(0, 0); }
          50% { transform: translate(20px, -20px); }
        }
        @keyframes float2 {
          0%, 100% { transform: translate(0, 0); }
          50% { transform: translate(-20px, 20px); }
        }
      `}</style>

      <div style={{
        position: 'relative', zIndex: 1,
        width: '100%',
        maxWidth: '700px',
        maxHeight: '90vh',
        overflowY: 'auto',
        padding: '24px'
      }}>
        <div style={{ fontSize: '22px', fontWeight: '500', marginBottom: '20px', color: '#ffffff' }}>Orders</div>
        {orders.length === 0 ? (
          <p style={{ color: '#999999' }}>No orders found</p>
        ) : (
          orders.map(order => (
            <div key={order.id} style={{
              background: 'rgba(42, 42, 42, 0.85)',
              border: '1px solid #444444',
              borderRadius: '12px',
              padding: '16px',
              marginBottom: '12px',
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              backdropFilter: 'blur(10px)'
            }}>
              <div>
                <div style={{ fontSize: '15px', fontWeight: '500', color: '#ffffff' }}>Order #{order.id}</div>
                <div style={{ fontSize: '13px', color: '#999999', marginTop: '4px' }}>
                  User ID: {order.userId} — {order.orderDate}
                </div>
              </div>
              <span style={{
                fontSize: '11px', padding: '4px 10px',
                borderRadius: '8px',
                background: '#1a3a1a',
                color: '#4caf50'
              }}>Completed</span>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default Orders;
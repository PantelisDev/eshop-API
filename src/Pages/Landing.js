import React, { useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';

function Landing() {
  const canvasRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext('2d');
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    const particles = [];
    for (let i = 0; i < 80; i++) {
      particles.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        radius: Math.random() * 2 + 0.5,
        speedX: (Math.random() - 0.5) * 0.5,
        speedY: (Math.random() - 0.5) * 0.5,
        opacity: Math.random() * 0.5 + 0.1
      });
    }

    let animId;
    const animate = () => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      particles.forEach(p => {
        p.x += p.speedX;
        p.y += p.speedY;
        if (p.x < 0) p.x = canvas.width;
        if (p.x > canvas.width) p.x = 0;
        if (p.y < 0) p.y = canvas.height;
        if (p.y > canvas.height) p.y = 0;

        ctx.beginPath();
        ctx.arc(p.x, p.y, p.radius, 0, Math.PI * 2);
        ctx.fillStyle = `rgba(249, 115, 22, ${p.opacity})`;
        ctx.fill();
      });

      // draw lines between close particles
      particles.forEach((p1, i) => {
        particles.slice(i + 1).forEach(p2 => {
          const dx = p1.x - p2.x;
          const dy = p1.y - p2.y;
          const dist = Math.sqrt(dx * dx + dy * dy);
          if (dist < 120) {
            ctx.beginPath();
            ctx.moveTo(p1.x, p1.y);
            ctx.lineTo(p2.x, p2.y);
            ctx.strokeStyle = `rgba(249, 115, 22, ${0.1 * (1 - dist / 120)})`;
            ctx.lineWidth = 0.5;
            ctx.stroke();
          }
        });
      });

      animId = requestAnimationFrame(animate);
    };

    animate();

    const handleResize = () => {
      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;
    };
    window.addEventListener('resize', handleResize);

    return () => {
      cancelAnimationFrame(animId);
      window.removeEventListener('resize', handleResize);
    };
  }, []);

  return (
    <div style={{
      position: 'fixed',
      top: '0', left: '0', right: '0', bottom: '0',
      background: '#1a1a1a',
      display: 'flex',
      flexDirection: 'column',
      justifyContent: 'center',
      alignItems: 'center',
      textAlign: 'center',
      padding: '40px 24px',
      overflow: 'hidden'
    }}>
      <canvas ref={canvasRef} style={{
        position: 'absolute',
        top: 0, left: 0,
        pointerEvents: 'none'
      }} />

      {/* Orbs */}
      <div style={{
        position: 'absolute', width: '400px', height: '400px',
        borderRadius: '50%', background: '#f97316',
        filter: 'blur(100px)', opacity: 0.12,
        top: '-100px', left: '-100px',
        animation: 'float1 8s ease-in-out infinite',
        pointerEvents: 'none'
      }} />
      <div style={{
        position: 'absolute', width: '350px', height: '350px',
        borderRadius: '50%', background: '#6366f1',
        filter: 'blur(100px)', opacity: 0.12,
        bottom: '-100px', right: '-100px',
        animation: 'float2 8s ease-in-out infinite',
        pointerEvents: 'none'
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

      <div style={{ position: 'relative', zIndex: 1 }}>
        <div style={{
          display: 'inline-block',
          fontSize: '12px', color: '#f97316',
          border: '1px solid #f97316',
          borderRadius: '20px',
          padding: '4px 14px',
          marginBottom: '24px'
        }}>✦ New arrivals every week</div>

        <div style={{
          fontSize: '52px', fontWeight: '600',
          color: '#ffffff', lineHeight: '1.2',
          marginBottom: '16px', maxWidth: '600px'
        }}>
          Shop the latest<br />
          <span style={{ color: '#ffffff' }}>tech products</span>
        </div>

        <div style={{
          fontSize: '16px', color: '#999999',
          marginBottom: '40px', maxWidth: '480px',
          lineHeight: '1.6'
        }}>
          Discover premium computers, phones and peripherals at unbeatable prices. Fast delivery, easy returns.
        </div>

        <div style={{ display: 'flex', gap: '16px', justifyContent: 'center' }}>
          <button onClick={() => navigate('/products')} style={{
            padding: '12px 32px',
            borderRadius: '8px',
            border: 'none',
            background: '#f97316',
            color: '#ffffff',
            fontSize: '15px', fontWeight: '500',
            cursor: 'pointer'
          }}>Shop now</button>
        </div>

        <div style={{ display: 'flex', gap: '48px', marginTop: '60px', justifyContent: 'center' }}>
          <div>
            <div style={{ fontSize: '28px', fontWeight: '600', color: '#ffffff' }}>15+</div>
            <div style={{ fontSize: '13px', color: '#999999', marginTop: '4px' }}>Products</div>
          </div>
          <div>
            <div style={{ fontSize: '28px', fontWeight: '600', color: '#ffffff' }}>3</div>
            <div style={{ fontSize: '13px', color: '#999999', marginTop: '4px' }}>Categories</div>
          </div>
          <div>
            <div style={{ fontSize: '28px', fontWeight: '600', color: '#ffffff' }}>100%</div>
            <div style={{ fontSize: '13px', color: '#999999', marginTop: '4px' }}>Satisfaction</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Landing;
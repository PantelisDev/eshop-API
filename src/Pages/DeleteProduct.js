import React, { useState, useEffect } from 'react';

function DeleteProduct() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/products')
      .then(res => res.json())
      .then(data => setProducts(data.data));
  }, []);

  const handleDelete = (id, title) => {
    if (window.confirm('Are you sure you want to delete ' + title + '?')) {
      fetch('http://localhost:8080/products/' + id, {
        method: 'DELETE'
      })
      .then(() => {
        setProducts(products.filter(p => p.id !== id));
        alert(title + ' deleted successfully!');
      });
    }
  };

  return (
    <div>
      <h2>Delete Product</h2>
      {products.map(product => (
        <div key={product.id}>
          <span>{product.title} - ${product.price}</span>
          <button onClick={() => handleDelete(product.id, product.title)}>Delete</button>
          <hr/>
        </div>
      ))}
    </div>
  );
}

export default DeleteProduct;
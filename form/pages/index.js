import React, { useState } from 'react';
// import './display.css';


const MyForm = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNO, setPhoneNo] = useState('');
  const [password, setPassword] = useState('');
  const [dob, setDob] = useState('');
  const [message, setMessage] = useState('');
  const [validate, setValidate] = useState({});
  const [csvContent, setCsvContent] = useState([]);
  const [gender, setGender] = useState('');


  const handleSubmit = (event) => {
    event.preventDefault();

    if (event.target.csvFile.files.length > 0) {
        const file = event.target.csvFile.files[0];
        const reader = new FileReader();
  
        reader.onload = (event) => {
          const content = event.target.result;
          parseCsvContent(content);     
        };
  
        reader.readAsText(file);
      }
    
    const validate = {};
    if (!name) {
      validate.name = 'Name is required';
    }
    if (!email) {
      validate.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      validate.email = 'Email is invalid';
    }
    if (!phoneNO) {
      validate.phoneNO = 'Phone Number is required';
    } else if (!/^\d{10}$/.test(phoneNO)) {
      validate.phoneNO = 'Phone Number is invalid';
    }
    if (!password) {
      validate.password = 'Password is required';
    } else if (password.length < 8) {
      validate.password = 'Password must be at least 8 characters long';
    }
    if (!dob) {
      validate.dob = 'Date of Birth is required';
    }
    setValidate(validate);

    if (Object.keys(validate).length === 0) {
      console.log('Form submitted:', { name, email, message, phoneNO, password, dob, gender });
      setName('');
      setEmail('');
      setMessage('');
      setPhoneNo('');
      setPassword('');
      setDob('');
      setGender('');
    }
  };
  

  const parseCsvContent = (content) => {
    const rows = content.split('\n');
    const data = [];

    rows.forEach(row => {
      const columns = row.split(',');
      data.push(columns);
    });
    localStorage.setItem('csvData', JSON.stringify(data));
    setCsvContent(data);
  };

  return (
    <div>
      <h1 data-testid='head'>Form</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Name:</label>
          <input
            id="name"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
          {validate.name && <span>{validate.name}</span>}
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          {validate.email && <span>{validate.email}</span>}
        </div>
        <div>
          <label htmlFor="phoneNO">Phone Number:</label>
          <input
            id="phoneNO"
            type="number"
            value={phoneNO}
            onChange={(e) => setPhoneNo(e.target.value)}
            required
          />
          {validate.phoneNO && <span>{validate.phoneNO}</span>}
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          {validate.password && <span>{validate.password}</span>}
        </div>
        <div>
          <label htmlFor="dob">Date of Birth:</label>
          <input
            id="dob"
            type="date"
            value={dob}
            onChange={(e) => setDob(e.target.value)}
            required
          />
          {validate.dob && <span>{validate.dob}</span>}
        </div>
        <div>
          <label htmlFor="message">Message:</label>
          <textarea
            id="message"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
          ></textarea>
        </div>
        <div>
          <label >Gender:</label>
          <div>
            <label htmlFor="male" data-testid='male'>
              <input
                type="radio"
                value="male"
                id="male"
                checked={gender === 'male'}
                onChange={(e) => setGender(e.target.value)}
              /> Male
            </label>
            <label   htmlFor="female" data-testid='female'>
              <input
                type="radio"
                value="female"
                id="female"
                checked={gender === 'female'}
                onChange={(e) => setGender(e.target.value)}
              /> Female
            </label>
          </div>
        </div>
        <div>
          <label htmlFor="csvFile">Upload CSV File:</label>
          <input
            id="csvFile"
            type="file"
            accept=".csv"
          />
        </div>
        <button type="submit" data-testid="submit">Submit</button>
      </form>
      <div>
        <h2>CSV Content</h2>
        <table>
          <thead>
            <tr>
              {csvContent.length > 0 && csvContent[0].map((item, index) => (
                <th key={index}>{item}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {csvContent.slice(1).map((row, rowIndex) => (
              <tr key={rowIndex}>
                {row.map((col, colIndex) => (
                  <td key={colIndex}>{col}</td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MyForm;

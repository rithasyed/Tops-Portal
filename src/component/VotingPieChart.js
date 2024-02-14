import React from 'react';
import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts';

const data = [
  { name: 'Option 1', value: 40 },
  { name: 'Option 2', value: 30 },
  { name: 'Option 3', value: 20 },
  { name: 'Option 4', value: 10 },
];

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

const VotingPieChart = () => {
  return (
    <ResponsiveContainer width="100%" height={400}>
      <PieChart>
        <Pie
          data={data}
          cx="50%"
          cy="50%"
          labelLine={false}
          outerRadius={150}
          fill="#8884d8"
          dataKey="value"
          label={({ cx, cy, midAngle, innerRadius, outerRadius, percent, index }) => {
            const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
            const x = cx + radius * Math.cos(-midAngle * (Math.PI / 180));
            const y = cy + radius * Math.sin(-midAngle * (Math.PI / 180));
            return (
              <text
                x={x}
                y={y}
                fill="white"
                fontSize="14px"
                fontWeight="bold"
                textAnchor={x > cx ? 'start' : 'end'}
                dominantBaseline="central"
              >
                {`${data[index].value}%`}
              </text>
            );
          }}
          
        >
          {data.map((entry, index) => (
            <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} stroke="#fff" strokeWidth={2} />
          ))}
        </Pie>
        <Legend align="center" verticalAlign="bottom" layout="horizontal" />
        <Tooltip />
      </PieChart>
    </ResponsiveContainer>
  );
};

export default VotingPieChart;

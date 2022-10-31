import { Box } from '@mantine/core';
import React, { useEffect, useState } from 'react'
import Plot from 'react-plotly.js'
import AppService from '../services/AppService';


interface Props {
  name: string;
}

const PlotDiagram: React.FC<Props> = (props) =>{
  
  const [Pi, setPi] = useState([]);
  const [Pv, setPv] = useState([]);
  const [Pm, setPm] = useState([]);
  const [Pr, setPr] = useState([]);
  const [days, setDays] = useState([]);
  
 
  useEffect(() => {
    AppService.getPiByName(props.name).then((res) => {setPi(res.data)})
  },([props.name]));

  useEffect(() => {
    AppService.getPvByName(props.name).then((res) => {setPv(res.data)})
  },([props.name]));

  useEffect(() => {
    AppService.getPmByName(props.name).then((res) => {setPm(res.data)})
  },([props.name]));

  useEffect(() => {
    AppService.getPrByName(props.name).then((res) => {setPr(res.data)})
  },([props.name]));

  useEffect(() => {
    AppService.getDaysByName(props.name).then((res) => {setDays(res.data)})
  },([props.name]));

    return (
      <Box>
        <Plot
          data={[
            {
              x: days,
              y: Pi,
              type: 'scatter',
              mode: 'lines',
              marker: {color: 'red'},
              name: 'Pi - Liczba osób zarażonych'
            },
            {
              x: days,
              y: Pv,
              type: 'scatter',
              mode: 'lines',
              marker: {color: 'green'},
              name: 'Pv - Liczba osób zdrowych, podatnych na infekcję'
            },
            {
              x: days,
              y: Pm,
              type: 'scatter',
              mode: 'lines',
              marker: {color: 'blue'},
              name: 'Pm - Liczba osób zmarłych'
            },
            {
              x: days,
              y: Pr,
              type: 'scatter',
              mode: 'lines',
              marker: {color: 'yellow'},
              name: 'Pr - Liczba osób, które wyzdrowiały i nabyły odporność'
            },
          
          ]}
          layout={ {width: 1300, height: 500, title: "Nazwa symulacji:"+props.name} }
        />
      </Box>
      );
}
export default PlotDiagram;

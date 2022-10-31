import React, { useState } from 'react'
import { useForm } from '@mantine/form';
import { Box, Button, Group, NumberInput, Slider, TextInput,Text, Alert } from '@mantine/core';
import AppService from '../services/AppService';



interface FormValues {
    name: string,
    P: number,
    I: number,
    R: number,
    M: number,
    Ti: number,
    Tm: number,
    Ts: number
}
export default function Input() {
    const [message, setMessage] = useState(null);
    const form = useForm({
        initialValues: {
            name: '',
            P:  0,
            I:  0,
            R:  0,
            M:  0,
            Ti: 0,
            Tm: 0,
            Ts: 0
    }});

    function onFormSubmit(values: FormValues) {
       console.log(values)
       AppService.createNewSimulation(values.name,values.P,values.I,values.R,values.M,values.Ti,values.Tm,values.Ts).catch((error: any) => { 
          console.log(error.response.data)
          setMessage(error.response.data)
       })     
      window.location.reload() 
    }
    
  return (
    <Box sx={{ maxWidth: 1000}} mx="auto">
    <form  onSubmit={form.onSubmit(onFormSubmit)}>
            <TextInput
            required
            label="Unikalna nazwa symulacji"
            {...form.getInputProps('name')}
            />
            <NumberInput
            required
            label="Wilekość populacji"
            {...form.getInputProps('P')}
            />
           <NumberInput
            required
            label="Początkowa liczba osób zarażonych"
            {...form.getInputProps('I')}
            />
           <NumberInput
            required
            label="Ile osób zaraża jedna zarażona osoba"
            precision={2}
            {...form.getInputProps('R')}
            />
            <NumberInput
            required
            label="Wskaźnik śmiertelności, określający ilu spośród zarażonych umiera"
            precision={2}
            {...form.getInputProps('M')}
            />
            <NumberInput
            required
            label="Ilość dni, która upływa od momentu zarażenia do wyzdrowienia chorego"
            {...form.getInputProps('Ti')}
            />
            <NumberInput
            required
            label="Ilość dni, która upływa od momentu zarażenia do śmierci chorego"
            {...form.getInputProps('Tm')}
            />
            <NumberInput
            required
            label="Ilość dni, dla których ma być przeprowadzona symulacja"
            {...form.getInputProps('Ts')}
            />
            <Group position="right" mt="md">
                    <Button type="submit">Submit</Button>
            </Group>
    </form>
    </Box>
  )
}

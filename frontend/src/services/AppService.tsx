import axios from 'axios';
import { render } from '@testing-library/react';
import React from 'react';

const TICKET_API_BASE_URL = "http://localhost:8080/"; 


class AppService {
    
    createNewSimulation(name: string, P: number, I: number,  R: number,  M: number,  Ti: number,  Tm: number,  Ts: number) {
        return axios.post(TICKET_API_BASE_URL + "createNewSimulation" + '/' + name + '/' +P+ '/' + I + '/' + R + '/' + M + '/' +  Ti + '/' + Tm + '/' + Ts).
        catch((error: any) => {
            if(error.response){
                // console.log(error.response.data)   
                // return error.response.data      
            };
        })
    }
    
    getModelsByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getByAppModelName/" + name)
    }
    getPiByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getPiByAppModelName/" + name)
    }
    getPvByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getPvByAppModelName/" + name)
    }
    getPmByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getPmByAppModelName/" + name)
    }
    getPrByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getPrByAppModelName/" + name)
    }
    getDaysByName(name: string){
        return axios.get(TICKET_API_BASE_URL + "getDaysByAppModelName/" + name)
    }
    getAllNames(){
        return axios.get(TICKET_API_BASE_URL + "getAllNames")
    }
}

export default new AppService()


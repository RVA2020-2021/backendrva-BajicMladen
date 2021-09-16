import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PROJEKAT_URL } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ProjekatService {

  constructor(private httpClient:HttpClient) { }

  public getAllProjekti():Observable<any>{
    return this.httpClient.get(`${PROJEKAT_URL}`)
  }
}

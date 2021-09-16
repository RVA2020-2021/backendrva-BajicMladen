import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Projekat } from 'src/app/models/projekat';
import { ProjekatService } from 'src/app/services/projekat.service';

@Component({
  selector: 'app-projekat',
  templateUrl: './projekat.component.html',
  styleUrls: ['./projekat.component.css']
})
export class ProjekatComponent implements OnInit,OnDestroy {

  displayedColumns = ['id','naziv','opis','oznaka','actions'];
  dataSource: MatTableDataSource<Projekat>;
  subscription: Subscription;

  constructor(private projekatService: ProjekatService) { }

  ngOnInit(): void {
    this.loadData()
  }

  ngOnDestroy():void{
    this.subscription.unsubscribe();
  }

  public loadData(){
    this.subscription = this.projekatService.getAllProjekti().subscribe(
      data =>{
        this.dataSource = new MatTableDataSource(data)
      }
    ),
    (error:Error)=>{
      console.log(error.name+' '+ error.message)
    }
  }

}

import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Grupa } from 'src/app/models/grupa';
import { Smer } from 'src/app/models/smer';
import { GrupaService } from 'src/app/services/grupa.service';
import { GrupaDialogComponent } from '../dialogs/grupa-dialog/grupa-dialog.component';

@Component({
  selector: 'app-grupa',
  templateUrl: './grupa.component.html',
  styleUrls: ['./grupa.component.css']
})
export class GrupaComponent implements OnInit, OnDestroy {

  displayedColumns =['id','oznaka','smer'];
  dataSource: MatTableDataSource<Grupa>;
  subscription: Subscription;

  constructor(private grupaService: GrupaService, 
    private dialog:MatDialog) { }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }
  
  loadData(){
    this.subscription = this.grupaService.getAllGrupe().subscribe(data =>{
      this.dataSource = new MatTableDataSource(data);
    }),
    (error: Error)=>{
      console.log(error.name+' '+ error.message)
    }
  }

  public openDialog(flag: number,id?: number,oznaka?:string,smer?: Smer){
    const dialogRef = this.dialog.open(GrupaDialogComponent,{data:{
      id,oznaka,smer
    }});
    dialogRef.componentInstance.flag=flag;
    dialogRef.afterClosed().subscribe(res =>{
      if(res==1){
        this.loadData();
      }
    })
  }

  selectRow(row: any){

  }
}

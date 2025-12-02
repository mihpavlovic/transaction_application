import { Component, inject, OnInit } from '@angular/core';
import { TransactionModel } from '../../models/transaction';
import { TransactionService } from '../../services/transaction-service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-transaction-component',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './transaction-component.html',
  styleUrl: './transaction-component.css',
})
export class TransactionComponent implements OnInit{

  allTransactions: TransactionModel[] = [];
  transactionForm!: FormGroup;
  searchText: String = "";
  searchedTransactions: TransactionModel[] = [];

  private transactionService = inject(TransactionService);
  private formBuilder = inject(FormBuilder);

  ngOnInit(): void {
    this.transactionForm = this.formBuilder.group({
      transactionDate: ['', Validators.required],
      accountNumber: ['', [Validators.required, Validators.pattern(/^\d{4}-\d{4}-\d{4}$/)]],
      accountHolderName: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(0.01)]],
    });
    
    this.transactionService.getAllTransactions().subscribe(
      data => {
        this.allTransactions = data;
        console.log(this.allTransactions[0].accountHolderName);
        this.searchedTransactions = data;
      }
    )
  }

  saveTransaction() {
    if(this.transactionForm.invalid){
      this.transactionForm.markAllAsTouched();
      return;
    }

    const randomStatus = ['Pending', 'Settled', 'Failed'][Math.floor(Math.random() * 3)];

    const newTransaction: TransactionModel = {
      ...this.transactionForm.value,
      status: randomStatus
    };

    this.transactionService.addTransaction(newTransaction).subscribe(
      data => {
        if(data === 'Success'){
          this.allTransactions.push(newTransaction);
          const closeBtn = document.getElementById('transactionModalCloseBtn');
          if (closeBtn) closeBtn.click();
          this.transactionForm.reset();
        } else {
          alert(data);
          console.log(data);
        }
      }
    );

  }

  searchTransactions(){
    const text = this.searchText.toLowerCase();
    this.searchedTransactions = this.allTransactions.filter(
      tr => tr.accountHolderName.toLowerCase().includes(text)
    );
  }

}

import { Component, inject, OnInit } from '@angular/core';
import { TransactionModel } from '../../models/transaction';
import { TransactionService } from '../../services/transaction-service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-transaction-component',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './transaction-component.html',
  styleUrl: './transaction-component.css',
})
export class TransactionComponent implements OnInit{

  allTransactions: TransactionModel[] = [];
  transactionForm!: FormGroup;

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

}

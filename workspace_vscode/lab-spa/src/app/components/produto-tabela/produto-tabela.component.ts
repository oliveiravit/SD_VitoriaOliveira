import { Component, OnInit } from '@angular/core';
import { ProdutoDTO } from 'src/app/dtos/produto.dto';
import { ProdutoService } from 'src/app/services/produto.service';

@Component({
  selector: 'app-produto-tabela',
  templateUrl: './produto-tabela.component.html',
  styleUrls: ['./produto-tabela.component.scss'],
})
export class ProdutoTabelaComponent implements OnInit {
  produtos: ProdutoDTO[] = [];
  constructor(private service: ProdutoService) {}

  ngOnInit(): void {
    this.service.findAll().subscribe({
      next: (data) => (this.produtos = data),
      error: (e) => console.error(e),
    });

    this.produtos = [
      { id: 1, descricao: 'Furadeira', preco: 800.0 },
      { id: 2, descricao: 'Lixadeira', preco: 350.0 },
      { id: 3, descricao: 'Serra Circular', preco: 500.0 },
    ];
  }
}

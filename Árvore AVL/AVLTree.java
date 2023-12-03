public class AVLTree {
  AVLNode root;

  // Função para calcular a altura de um nó
  int height(AVLNode node) {
    if (node == null)
      return 0;
    return node.height;
  }

  // Função para obter o fator de equilíbrio de um nó
  int getBalance(AVLNode node) {
    if (node == null)
      return 0;
    return height(node.left) - height(node.right);
  }

  // Função para realizar a rotação à direita
  AVLNode rightRotate(AVLNode y) {
    AVLNode x = y.left;
    AVLNode T2 = x.right;

    // Realiza a rotação
    x.right = y;
    y.left = T2;

    // Atualiza as alturas
    y.height = Math.max(height(y.left), height(y.right)) + 1;
    x.height = Math.max(height(x.left), height(x.right)) + 1;

    return x;
  }

  // Função para realizar a rotação à esquerda
  AVLNode leftRotate(AVLNode x) {
    AVLNode y = x.right;
    AVLNode T2 = y.left;

    // Realiza a rotação
    y.left = x;
    x.right = T2;

    // Atualiza as alturas
    x.height = Math.max(height(x.left), height(x.right)) + 1;
    y.height = Math.max(height(y.left), height(y.right)) + 1;

    return y;
  }

  // Função para inserir um nó na árvore AVL
  AVLNode insert(AVLNode node, int data) {
    // Passo 1: Insere como em uma BST padrão
    if (node == null)
      return new AVLNode(data);

    if (data < node.data)
      node.left = insert(node.left, data);
    else if (data > node.data)
      node.right = insert(node.right, data);
    else // Dados iguais não são permitidos
      return node;

    // Passo 2: Atualiza a altura do nó atual
    node.height = 1 + Math.max(height(node.left), height(node.right));

    // Passo 3: Obtém o fator de equilíbrio deste nó para verificar se ele se tornou
    // desequilibrado
    int balance = getBalance(node);

    // Passo 4: Se o nó se tornar desequilibrado, existem quatro casos

    // Caso da esquerda esquerda
    if (balance > 1 && data < node.left.data)
      return rightRotate(node);

    // Caso da direita direita
    if (balance < -1 && data > node.right.data)
      return leftRotate(node);

    // Caso da esquerda direita
    if (balance > 1 && data > node.left.data) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    // Caso da direita esquerda
    if (balance < -1 && data < node.right.data) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    // Retorna o nó (inalterado ou após a rotação)
    return node;
  }

  // Função para percorrer a árvore em ordem e imprimir os dados
  void inOrder(AVLNode node) {
    if (node != null) {
      inOrder(node.left);
      System.out.print(node.data + " ");
      inOrder(node.right);
    }
  }
}
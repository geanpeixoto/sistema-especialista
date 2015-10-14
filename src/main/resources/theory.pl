/* OPERADORAS */
operadora(tim, 'TIM').
operadora(oi, 'Oi').
operadora(vivo, 'Vivo').
operadora(gvt, 'GVT').
operadora(certto, 'Certto').
operadora(net, 'Net').

/* SERVICOS */
servico(jogosonline, 'Jogos online (Live, PSN, LoL)').
servico(streamingvideo, 'Streaming de Video (Youtube, Netflix, Popcorntime)').
servico(streamingaudio, 'Streaming de Audio (Spotify, Deezer, Rdio)').
servico(armazenamento, 'Armazenamento em núvem (Google Drive, Dropbox, OneDrive)').
servico(redesocial, 'Redes sociais (Facebook, Instagran, Google Plus)').
servico(chat, 'Mensageiros (Whatsapp, Hangouts, Telegram)').
servico(torrent, 'Torrent').
servico(noticias, 'Portais de notícias (G1, Terra, R7)').

servico_upload(jogosonline, 1).
servico_upload(streamingvideo, 1).
servico_upload(streamingaudio, 1).
servico_upload(armazenamento, 2).
servico_upload(redesocial, 2).
servico_upload(chat, 1).
servico_upload(torrent, 3).
servico_upload(noticias, 1).

servico_download(jogosonline, 1).
servico_download(streamingvideo, 3).
servico_download(streamingaudio, 2).
servico_download(armazenamento, 2).
servico_download(redesocial, 3).
servico_download(chat, 2).
servico_download(torrent, 3).
servico_download(noticias, 2).

/*
## download = soma(download)*(1+(numeropessoas-1)*.5)
## upload = soma(upload)*(1+(numeropoas-1)*.5)
## franquia = soma(download)*(1+(numeropessoas-1)*.4)*srqt(horas);
*/

/* CONEXÕES */
conexao(g3, movel).
conexao(g4, movel).
conexao(adsl2, domestica).
conexao(fibra, domestica).

/* VELOCIDADE DE CONEXAO POR OPERADORA */
velocidade_conexao(g4, oi, 5, mb, 512, kb) :- !.
velocidade_conexao(g3, oi, 1, mb, 128, kb) :- !.
velocidade_conexao(g4, vivo, 5, mb, 500, kb) :- !.
velocidade_conexao(g3, vivo, 1, mb, 100, kb) :- !.

/* VELOCIDADE DE CONEXAO PADRÃO */
velocidade_conexao(g4, _, 5, mb, 500, kb).
velocidade_conexao(g3, _, 1, mb, 100, kb).

/* plano(operadora, conexao, nome, preco, franquia, unidade_franquia) */
plano_franquia(tim, g3, 'Liberty Express', 74.9, 600, mb).
plano_franquia(tim, g3, 'Liberty', 97.7, 600, mb).
plano_franquia(tim, g3, 'Liberty', 112.7, 1, gb).
plano_franquia(tim, g3, 'Liberty', 199.7, 3, gb).
plano_franquia(tim, g3, 'Liberty', 288.7, 6, gb).
plano_franquia(tim, g4, 'Liberty Top', 112.7, 6, gb).
plano_franquia(oi, g4, 'Pós-pago', 19.9, 300, mb).
plano_franquia(oi, g4, 'Pós-pago', 29.9, 500, mb).
plano_franquia(oi, g4, 'Pós-pago', 39.9, 2, gb).
plano_franquia(oi, g4, 'Pós-pago', 59.9, 5, gb).
plano_franquia(vivo, g4, 'Pós-pago', 89.99, 1, gb).
plano_franquia(vivo, g4, 'Pós-pago', 149.99, 4, gb).
plano_franquia(vivo, g4, 'Pós-pago', 224.99, 6, gb).
plano_franquia(vivo, g4, 'Pós-pago', 299.9, 8, gb).
plano_franquia(vivo, g4, 'Modem', 39.99, 1, gb).
plano_franquia(vivo, g4, 'Modem', 59.99, 2, gb).
plano_franquia(vivo, g4, 'Modem', 79.99, 6, gb).
plano_franquia(vivo, g4, 'Modem', 109.99, 12, gb).
plano_franquia(net, g3, 'Multi internet', 22.45, 1, gb).
plano_franquia(net, g3, 'Multi internet', 28.85, 2, gb).

plano_velocidade(oi, adsl2, 'Banda Larga', 49.9, 2, mb).
plano_velocidade(oi, adsl2, 'Banda Larga', 59.9, 10, mb).
plano_velocidade(oi, adsl2, 'Banda Larga', 69.9, 15, mb).
plano_velocidade(oi, adsl2, 'Banda Larga', 79.9, 25, mb).
plano_velocidade(oi, adsl2, 'Banda Larga', 89.9, 35, mb).
plano_velocidade(gvt, adsl2, 'Banda Larga', 69.9, 15, mb, 1, mb).
plano_velocidade(gvt, adsl2, 'Banda Larga', 79.9, 25, mb, 2, mb).
plano_velocidade(gvt, adsl2, 'Banda Larga', 89.9, 35, mb, 3, mb).
plano_velocidade(gvt, adsl2, 'Banda Larga', 99.9, 50, mb, 5, mb).
plano_velocidade(gvt, fibra, 'Banda Larga', 199.9, 150, mb, 50, mb).
plano_velocidade(certto, adsl2, 'Banda Larga', 49.9, 2, mb, 0.6, mb).
plano_velocidade(certto, adsl2, 'Banda Larga', 59.9, 3, mb, 0.7, mb).
plano_velocidade(certto, adsl2, 'Banda Larga', 69.9, 4, mb, 0.8, mb).
plano_velocidade(certto, adsl2, 'Banda Larga', 79.9, 5, mb, 0.9, mb).
plano_velocidade(certto, adsl2, 'Gamer', 89.9, 4, mb, 2, mb).
plano_velocidade(certto, adsl2, 'Gamer', 99.9, 6, mb, 2, mb).
plano_velocidade(certto, adsl2, 'Gamer', 149.9, 6, mb, 6, mb).
plano_velocidade(certto, adsl2, 'Gamer', 349.9, 15, mb, 5, mb).
plano_velocidade(certto, fibra, 'Fibra', 89.9, 10, mb, 2, mb).
plano_velocidade(certto, fibra, 'Fibra', 99.9, 15, mb, 4, mb).
plano_velocidade(certto, fibra, 'Fibra', 109.9, 25, mb, 6, mb).
plano_velocidade(certto, fibra, 'Fibra', 119.9, 35, mb, 8, mb).

plano(net, adsl2, 'Banda Larga', 29.8, 10, gb, 512, kb, 256, kb).
plano(net, adsl2, 'Banda Larga', 74.9, 30, gb, 2, mb, 500, kb).
plano(net, adsl2, 'Banda Larga', 89.9, 80, gb, 15, mb, 2, mb).
plano(net, adsl2, 'Banda Larga', 109.9, 100, gb, 30, mb, 2, mb).
plano(net, adsl2, 'Banda Larga', 139.9, 150, gb, 60, mb, 3, mb).
plano(net, fibra, 'Banda Larga', 319.9, 200, gb, 120, mb, 4, mb).

/**
 * Calcula a velocidade de upload para os planos que não constam essa informação:
 * VelocidadeUpload = VelocidadeDownload/10
 */
plano_velocidade(Operadora, Conexao, Nome, Valor, VelocidadeUpload, Unidade, VelocidadeDownload, Unidade) :-
  plano_velocidade(Operadora, Conexao, Nome, Valor, VelocidadeUpload, Unidade),
  VelocidadeDownload is VelocidadeUpload/10.

/**
 * Calcula a velocidade para os planos onde essa informação não e divulgada
 */
plano(Operadora, Conexao, Nome, Valor, Franquia, FUnidade, Download, DUnidade, Upload, UUnidade) :-
  plano_franquia(Operadora, Conexao, Nome, Valor, Franquia, FUnidade),
  velocidade_conexao(Conexao, Operadora, Download, DUnidade, Upload, UUnidade).

/**
 * Adiciona 2tb para a franquia de planos fixos
 */
plano(Operadora, Conexao, Nome, Valor, Franquia, FUnidade, Download, DUnidade, Upload, UUnidade) :-
  plano_velocidade(Operadora, Conexao, Nome, Valor, Download, DUnidade, Upload, UUnidade),
  FUnidade = gb,
  Franquia is 2048.

/**
 * Converte todas as unidades para kb
 */
parse(Number, kb, Result) :- Result is Number.
parse(Number, mb, Result) :- Number_ is Number*1024, parse(Number_, kb, Result).
parse(Number, gb, Result) :- Number_ is Number*1024, parse(Number_, mb, Result).

/**
 * Converte os planos para a mesma unidade (kb)
 */
plano_(Operadora, Conexao, Nome, Valor, Franquia_, Download_, Upload_) :-
  plano(Operadora, Conexao, Nome, Valor, Franquia, FUnidade, Download, DUnidade, Upload, UUnidade),
  parse(Franquia, FUnidade, Franquia_),
  parse(Download, DUnidade, Download_),
  parse(Upload, UUnidade, Upload_).

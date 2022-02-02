<<<<<<< HEAD
public GameTable gameTable(Player user, @DestinationVariable long id) throws Exception {
// call user repository
Player player = playerRepository.findById(user.getId()).get();
System.out.println("player" + player);
ArrayList<Player> players = new ArrayList<Player>(Arrays.asList(player));
Deck deck = new Deck();
GameTable gameTable = new GameTable(0.0, players, user.getBigBlindValue(), deck);
//        System.out.println("gametable" + gameTable.getId());
gameTableRepository.save(gameTable);
player.setGame_table(gameTable);
playerRepository.save(player);
System.out.println("created game. User: " + player.getUsername() + ". GameTable: " + gameTable.getId());
return gameTable;
=======
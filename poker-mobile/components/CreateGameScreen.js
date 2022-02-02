import React, { useEffect, useState } from "react";
import { View, Text, Button, StyleSheet, Alert, TextInput } from "react-native";

const CreateGameScreen = (props) => {

    const [gameKey, setgameKey] = useState();
    const [maxPlayers, setMaxPlayers] = useState();
    const [bigBlind, setBigBlind] = useState();
    const [minBuyIn, setMinBuyIn] = useState();

    useEffect(() => {
        setMinBuyIn(bigBlind * 10);
    }, [bigBlind])

    const handleSubmit = () => {
        if (
            gameKey == null || gameKey == "" || gameKey == undefined || gameKey == NaN || 
            maxPlayers == null || maxPlayers == "" || maxPlayers == undefined || maxPlayers == NaN || 
            bigBlind == null || bigBlind == "" || bigBlind == undefined || bigBlind == NaN || 
            minBuyIn == null || minBuyIn == "" || minBuyIn == undefined || minBuyIn == NaN 
        ) {
            Alert.alert(
                "Missing Fields",
                "Fill all fields, then press submit again",
                [
                    {
                        text: "Cancel",
                        style: "cancel"
                    },
                    { 
                        text: "OK", 
                    }
                ]
            );
        }
        else {
            // create a new game server side
            console.log("creating new game...")
            console.log(gameKey, maxPlayers, bigBlind, minBuyIn);
            props.navigation.navigate("Play", {
                "newGame": true,
                "gameKey": gameKey,
                "userId": props.route.params.userId,
                "bigBlind": bigBlind
            });
        }
    }

    return(
        <View style={styles.main}>
            <View style={styles.inputView}>
                <Text style={styles.text}>Game Key:</Text>
                <Text style={styles.smallText}>This will be what people use to join your game</Text>
                <TextInput
                    style={styles.input}
                    onChangeText={setgameKey}
                    value={gameKey}
                    placeholder="Game ID..."
                />
            </View>
            <View style={styles.inputView}>
                <Text style={styles.text}>Max Number Players:</Text>
                <TextInput
                    style={styles.input}
                    keyboardType="numeric"
                    onChangeText={setMaxPlayers}
                    value={maxPlayers}
                    placeholder="Max Players..."
                />
            </View>
            <View style={styles.inputView}>
                <Text style={styles.text}>Big Blind:</Text>
                <TextInput
                    style={styles.input}
                    keyboardType="numeric"
                    onChangeText={setBigBlind}
                    value={bigBlind}
                    placeholder="Max Players..."
                />                
            </View>
            <View style={styles.inputView}>
                <Text style={styles.text}>Minimum Buy In: {minBuyIn != NaN ? minBuyIn : null}</Text>
                <Text style={styles.smallText}>This is x10 your big blind</Text>
            </View>
            <View style={styles.buttonView}>
                <Button
                    title="Submit"
                    onPress={() => 
                        handleSubmit()
                    }
                />
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "7.5%",
    },
    inputView: {
        marginBottom: "4%",
    },
    input: {
      height: 40,
      margin: 12,
      borderWidth: 1,
      borderRadius: 10,
      padding: 10,
    },
    text: {
        textAlign: "center",
        fontSize: 20,
    },
    smallText: {
        textAlign: "center",
        fontSize: 15,
    },
    buttonView: {
        borderWidth: 0.5,
        marginLeft: "auto",
        marginRight: "auto",
        marginTop: "5%",
        padding: "1%",
        borderRadius: 10,
        borderColor: "blue",
    },
});

export default CreateGameScreen;

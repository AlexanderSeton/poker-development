import React, { useState } from "react";
import { View, Text, Button, TextInput, StyleSheet, Alert } from "react-native";

const JoinGameScreen = (props) => {

    const [gameId, setGameId] = useState();

    const handleSubmit = () => {
        if (gameId == null || gameId == "") {
            Alert.alert(
                "Missing Fields",
                "Enter game ID, then press submit again",
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
            // check with server if game id exists
            // if it does exist then connect to the specified game using user's game id
            console.log(gameId);

            // sending to GameScreen (where game is actually played)
            props.navigation.navigate("Play", {
                "gameKey": gameId,
                "userId": props.route.params.userId
            });
        }
    }

    return(
        <View style={styles.main}>
            <Text style={styles.text}>Game Id:</Text>
            <TextInput
                style={styles.input}
                onChangeText={setGameId}
                value={gameId}
                placeholder="Game ID..."
            />
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

export default JoinGameScreen;

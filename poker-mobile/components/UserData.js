import React from 'react';
import { View, Text, Button, TextInput, StyleSheet, Image } from "react-native";

const UserData = function(props) {

    console.log(props.user.bigBlind);

    return(
        <View style={styles.userDataView}>
            <Text style={styles.stackText}>
                Stack: £{props.user.stack}
            </Text>
            <Text style={styles.text}>
                Name: {props.user.name}
            </Text>
            <Text style={styles.text}>
                Username: {props.user.username}
            </Text>
            {props.user.bigBlind?
            // <Text>You are big Blind</Text>
            <Image
                source={require("../assets/bigBlind.png")}
                style={styles.image}
            />
            : null}
            {props.user.smallBlind?
            // <Text>You are small Blind</Text>
            <Image
                source={require("../assets/smallBlind.png")}
                style={styles.image}
            />
            : null}
        </View>

    );
}

const styles = StyleSheet.create({
    userDataView: {
        textAlign: "center",
        marginLeft: "auto",
        marginRight: "auto",
        paddingTop: "5%",
    },
    text: {
        textAlign: "center",
        fontSize: 15,
        paddingTop: "3%",
    },
    stackText: {
        textAlign: "center",
        fontSize: 20,
        paddingTop: "3%",
    },
    image: {
        resizeMode: "contain",
        width: null,
        height: 75,
        marginTop: "5%",
    },
});

export default UserData;

import React, { useEffect, useState } from "react";
import { View, Text, Button, StyleSheet } from "react-native";

const AccountScreen = (props) => {

    const [username, setUsername] = useState(props.userId);
    const [balance, setBalance] = useState();

    useEffect(async() => {
        let userId = await props.route.params.userId;
        const response = await (await fetch(`http://localhost:8080/players/${userId}`)).json();
        setUsername(response["name"]);
        setBalance(response["stack"]);
    }, [])

    return(
        <View style={styles.main}>
            <Text style={styles.text}>Username: {username}</Text>
            <Text style={styles.text}>Balance: £{balance}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    main: {
        paddingTop: "7.5%",
    },
    text: {
        textAlign: "center",
        fontSize: 25,
        padding: "3%",
        marginBottom: "5%",
    },
    smallText: {
        textAlign: "center",
        fontSize: 15,
    },
});

export default AccountScreen;
